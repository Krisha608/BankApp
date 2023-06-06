import java.util.Date;
import java.util.Scanner;

class Bank{
    private int custID,accountNo;
    private String custName,custUserName,custPassword,gender,maritialStatus;
    private String adminUser,adminPassword;
    private float balance,deposit,withdrawl;
    Bank(){
        balance=10000f;
        adminUser="admin";
        adminPassword="admin";
    }
    void depositAmount(){
        Scanner obj=new Scanner(System.in);
        System.out.println("Enter the amount you want to deposit");
        deposit=obj.nextFloat();
        if(deposit>0){
            balance=balance+deposit;
        }
        else {
            System.out.println("The deposit amount cannot be less than or equal to zero");
        }
    }
    void withAmount(){
        Scanner obj=new Scanner(System.in);
        System.out.println("Enter the amount you want to withdrawl");
        withdrawl=obj.nextFloat();
        float tempBalance=balance-withdrawl;
        if(tempBalance<10000){
            System.out.println("Insuffcient amount");
        }
        else{
            balance=tempBalance;
        }
    }
    void checkBalance(){
        if (gender.equalsIgnoreCase("MALE") && maritialStatus.equalsIgnoreCase("U")){
            System.out.println("Master "+custName+" Your balance is "+balance);
        }
        else if(gender.equalsIgnoreCase("MALE") && maritialStatus.equalsIgnoreCase("M")){
            System.out.println("Mr. "+custName+" Your balance is "+balance);
        }
        else if(gender.equalsIgnoreCase("FEMALE") && maritialStatus.equalsIgnoreCase("U")){
            System.out.println("Miss. "+custName+" Your balance is "+balance);
        }
        else {
            System.out.println("Mrs. "+custName+" Your balance is "+balance);
        }
    }
    int loginAdmin(String uAdmin,String passwordAdmin){
        if(uAdmin.equals(adminUser) && passwordAdmin.equals(adminPassword)){
            return 1;
        }
        return -1;
    }
    static void showAdminMenu(){
        System.out.println("1--->Create Customer");
        System.out.println("2--->Display Customer");
        System.out.println("3--->Search Customer");
        System.out.println("4--->logout");
    }
    void createCustomer(){
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter the custId");
        custID=obj.nextInt();
        System.out.println("Enter the account no");
        accountNo=obj.nextInt();
        System.out.println("Enter the customer name");
        custName=obj.next();
        System.out.println("Entet the username you want to set");
        custUserName=obj.next();
        System.out.println("Enter the password");
        custPassword=obj.next();
        System.out.println("Enter the gender");
        gender=obj.next();
        System.out.println("Emter the maritial status");
        maritialStatus= obj.next();
    }
    void displayCustomer(){
        System.out.println(custID+"\t\t\t"+accountNo+"\t\t\t"+custName+"\t\t\t"+custUserName+"\t\t\t"+custPassword+"\t\t\t\t"+balance);
    }
    int loginCustomer(String uName,String pass){
        if(uName.equals(custUserName) && pass.equals(custPassword)){
            return 1;
        }
        return -1;
    }
    int transferMoney(Bank objBank[],int acountNumber){
        for (int i=0;i<2;i++){
            if(objBank[i].accountNo==acountNumber){
                return i;
            }
        }
        return -1;
    }
    float returnBalance(){
        return balance;
    }
    void updateBalance(float bal){
        balance=bal;
    }
    static int search(Bank objBank[],int cid){
        for (int i=0;i<2;i++){
            if (objBank[i].custID==cid){
                return i;
            }
        }
        return -1;
    }
}
public class BankApplicationImplemention {
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        Bank objBank = new Bank();
        Bank objBank1[]=new Bank[2];
        while (true) {
            System.out.println("1--->Admin Login");
            System.out.println("2--->Customer Login");
            System.out.println("3--->exit");
            int choice = obj.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Please enter user name and password");
//                    Date objDate=new Date();
//                    System.out.println(objDate);
//                    if(objDate.toString().substring(0,3).equalsIgnoreCase("SUN")){
//                        System.out.println("It's Sunday");
//                    }
                    String uAdmin = obj.next();
                    String pAdmin = obj.next();
                    if (objBank.loginAdmin(uAdmin, pAdmin) == 1) {
                        Bank.showAdminMenu();
                        System.out.println("Enter the choice");
                        int choiceAdmin = obj.nextInt();
                        switch (choiceAdmin) {
                            case 1:
                                for (int i = 0; i < 2; i++) {
                                    objBank1[i] = new Bank();
                                    objBank1[i].createCustomer();
                                }
                                break;
                            case 2:
                                System.out.println("custId\taccountNo\tcustName\tcustUserName\tcustPassword\tbalance");
                                for (int i = 0; i < 2; i++) {
                                    objBank1[i].displayCustomer();
                                }
                                break;
                            case 3: int cid;
                                System.out.println("Enter the id you want to search");
                                cid=obj.nextInt();
                                if(Bank.search(objBank1,cid)>=0){
                                    System.out.println("Customer exits");
                                }
                                else{
                                    System.out.println("Doesn't Exits");
                                }
                                break;
                            case 4:
                                System.exit(0);
                        }
                    } else {
                        System.out.println("Sorry! You have entered wrong user and password");
                    }
                    break;

                case 2:
                    int flag = -1;
                    System.out.println("customer login");
                    System.out.println("Enter the user name and password");
                    String cUser = obj.next();
                    String cPassword = obj.next();
                    for (int i = 0; i < 2; i++) {
                        if (objBank1[i].loginCustomer(cUser, cPassword) == 1) {
                            flag = i;
                            break;
                        }
                    }
                    if (flag >= 0) {
                        System.out.println("1--->deposit");
                        System.out.println("2--->withdrawl");
                        System.out.println("3--->checkBalance");
                        System.out.println("4--->Tranfer");
                        System.out.println("5--->exit");
                        System.out.println("Enter the choice");
                        int key = obj.nextInt();
                        switch (key) {
                            case 1:
                                objBank1[flag].depositAmount();
                                break;
                            case 2:
                                objBank1[flag].withAmount();
                                break;
                            case 3:
                                objBank1[flag].checkBalance();
                                break;
                            case 4:
                                System.out.println("Enter the account where you want to transfer");
                                int acountNo=obj.nextInt();
                                int targetIndex=objBank1[flag].transferMoney(objBank1,acountNo);
                                if(targetIndex>=0){
                                    float amountTransfer;
                                    System.out.println("Enter the amount you want to transfer");
                                    amountTransfer=obj.nextFloat();
                                    objBank1[flag].updateBalance((objBank1[flag].returnBalance())-amountTransfer);
                                    objBank1[targetIndex].updateBalance((objBank1[targetIndex].returnBalance())+amountTransfer);
                                }
                                else {
                                    System.out.println("Target customer doesn't exists");
                                }
                                break;
                            case 5:
                                System.exit(0);
                        }
                    }
                    else {
                        System.out.println("incorrect");
                    }
                    break;
                case 3:
                    System.exit(0);
            }

        }
    }
}