package com.geektrust.backend.services;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.repository.CardRepository;
import com.geektrust.backend.repository.ICardRepository;
import com.geektrust.backend.repository.IPassengerRepository;
import com.geektrust.backend.repository.PassengerRepository;

public class MetroStationService {
    static IPassengerRepository passengerRepository=new PassengerRepository();
    static ICardRepository cardRepository=new CardRepository();
    static ICardService cardService = new CardService(cardRepository);
    static IPassengerService passengerService = new PassengerService(cardService,passengerRepository);
    static IPrintSummary printSummaryService = new PrintSummary(passengerService);

    
        public void start(String[] args) {
            List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
            String expectedSequence = "INPUT_FILE";
            String actualSequence = commandLineArgs.stream()
                    .map(a -> a.split("=")[0])
                    .collect(Collectors.joining("$"));
            if (expectedSequence.equals(actualSequence)) {
                run(commandLineArgs);
            }
        }
    
        public static void run(List<String> commandLineArgs) {

            BufferedReader reader;
            String inputFile = commandLineArgs.get(0).split("=")[1];
            commandLineArgs.remove(0);
           
            try {
                reader = new BufferedReader(new FileReader(inputFile));
                String line = reader.readLine();
                while (line != null) {
                    //System.out.println(line);
                
                    String[] input = line.split(" ", 2);
                    switch (input[0]) {
                        case "BALANCE":
                            String[] cardProperties = input[1].split(" ", 2);
                            // card number - cardProperties[0]
                            // card balance - Integer.parseInt(cardProperties[1])
                            cardRepository.saveCard(cardProperties[0], Integer.parseInt(cardProperties[1]));
                            break;
                        case "CHECK_IN":
                            String[] checkInDetails = input[1].split(" ", 3);
                            // card number - checkInDetails[0]
                            // passenger type - checkInDetails[1]
                            // from station - checkInDetails[2]
                            passengerService.passengerCheckIn((new Passenger(checkInDetails[0], checkInDetails[1], checkInDetails[2])));
                            break;
                        case "PRINT_SUMMARY":
                            // action
                            printSummaryService.printSummary();
                            break;
                        default:
                            break;
                    }
                    line = reader.readLine();
                   // System.out.println(line);
                }
            } catch (Exception e) {
                // Handle exceptions appropriately
                e.printStackTrace();
            }
        }
    }
    

