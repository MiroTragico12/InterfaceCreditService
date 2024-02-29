package application;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.PaypalService;

public class Program {

    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Entre com os dados do contrato: ");
        System.out.print("Numero do contrato: ");
        int contractNumber = sc.nextInt();
        System.out.print("Data (dd/MM/yyyy):");
        LocalDate date = LocalDate.parse(sc.next(), fmt);
        System.out.print("Valor do contrato: ");
        double contractValue = sc.nextDouble();

        Contract contract = new Contract(contractNumber, date, contractValue);

        System.out.println("Numero do contrato : " + contractNumber);
        System.out.println("Data do contrato: " + date.format(fmt));
        System.out.println("Valor do contrato: " + contractValue);

        System.out.println("Entre com o numero de parcelas: ");
        int numberInstallments = sc.nextInt();
        
        
        ContractService contractService = new ContractService(new PaypalService());
        
        contractService.processContract(contract, numberInstallments);
        
        System.out.println("PARCELAS: ");
        
        for ( Installment instalment : contract.getInstalments()) {
        	System.out.println(instalment);
        	
        }

        sc.close();
    }
}
