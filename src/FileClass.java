import java.io.*;
import java.util.Scanner;

public class FileClass {
    String path = "path.txt";
    File file = new File(path);
    String [] FullName = new String[10];
    String [] email = new String[10];
    int [] Age = new int[10];
    int count = 0;
    public void FileWriters(String [] FullName, String [] Email, int [] Age, int count) throws IOException
    {
        if(file.exists())
        {
            int counter = 1;
            try {
                FileWriter myWriter = new FileWriter(path);
                for(int i = 0; i < count; i++)
                {
                    myWriter.write(counter++ + " )  " + "FullName: " +FullName[i] + "\n" + "   Email Address: " +Email[i] + "\n" + "   Age: " + Age[i]);
                    myWriter.write("\n");
                }
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
    String res;
    public void AcceptMessage() throws IOException {
        do {
            Scanner scan = new Scanner(System.in);
            System.out.println("Please Enter your FullName : ");
            FullName[count] = scan.nextLine();
            System.out.println("Please Enter your Email: ");
            email[count] = scan.nextLine();
            System.out.println("Please Enter your Age: ");
            Age[count] = scan.nextInt();
            count++;

            res = Ask();

            if (res.equals("yes"))
            {
                caller();
                scan.close();
            }
            else if (res.equals("no"))
            {
                FileWriters(FullName,email,Age,count);
            }
            else if(!res.equals("yes") || !res.equals("no"))
            {
                do {
                    res = Ask();
                    if (res.equals("yes"))
                    {
                        caller();
                        scan.close();
                    }
                    else if (res.equals("no"))
                    {
                        System.out.println("Successfully wrote to the file.");
                    }
                }while (!res.equals("yes") || !res.equals("no"));
            }
        }
        while (res.equals("yes"));
    }
    public void caller() throws IOException
    {
        AcceptMessage();
    }
    public String Ask()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Do you want to continue: ");
        char [] choice = new char[3];
        choice = scan.next().toCharArray();

        res = new String(choice);
        return res;
    }
    public void ChooseOperation() throws IOException
    {
        System.out.println("********************************************************************************");
        System.out.println("*                                                                              *");
        System.out.println("*                                                                              *");
        System.out.println("*        1) Write File                                                         *");
        System.out.println("*        2) Read File                                                          *");
        System.out.println("*        3) Delete File                                                        *");
        System.out.println("*        4) Search Word                                                        *");
        System.out.println("*        5) Exit                                                               *");
        System.out.println("*                                                                              *");
        System.out.println("*                                                                              *");
        System.out.println("********************************************************************************");
        char Choice;
        do {
            Choice = Operation();
            switch (Choice)
            {
                case '1':
                    AcceptMessage();
                    ChooseOperationCaller();
                    break;
                case '2':
                    ReadFile();
                    ChooseOperationCaller();
                    break;
                case '3':
                    DeleteFile();
                    ChooseOperationCaller();
                    break;
                case '4':
                    SearchWord();
                    ChooseOperationCaller();
                    break;
                case '5':
                    System.out.println("Thank you for using this platform");
                    break;
                default:
                    do {
                        System.out.println("Please enter a correct operation");
                        Choice = Operation();
                        if (Choice != '1' || Choice != '2' || Choice != '3')
                        {
                            ChooseOperationCaller();
                            break;
                        }
                    }while (Choice != 'y' || Choice != 'n');
            }
        }while (Choice == 'y');
    }
    public void ChooseOperationCaller() throws IOException
    {
        ChooseOperation();
    }
    public void ReadFile() throws IOException
    {
        File file = new File(path);
        FileReader reader = new FileReader(file);
        Scanner scan = new Scanner(reader);
        while(scan.hasNextLine())
        {
            System.out.println(scan.nextLine());
        }
    }
    public void DeleteFile()
    {

    }
    public char Operation()
    {
        char choice;
        Scanner scan = new Scanner(System.in);
        choice = scan.next().charAt(0);
        return choice;
    }
    public void SearchWord() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the word you want to search: ");
        String search,str;
        search = scan.nextLine();
        FileReader reader = new FileReader(file);
        BufferedReader buffer = new BufferedReader(reader);
        File file = new File(path);
        try {
            while ( (str = buffer.readLine()) != null)
            {
                if(str.contains(search))
                {
                    System.out.println("The letter is found 😊😊😂 :"+search);
                    ChooseOperation();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
