//package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.Duration;


//-----------ASSUMPTION - PAY FINE MEANS THE MEMBER HAS RETURNED THE BOOK AND PAID ALL THE FINES
//----------------DATA STRUCTURE FOR MEMBER
class Member{
    String name;

    int age;
    String phone;



    long paid_fine;
    long due_fine;
    ArrayList<Kitabe> borrowed = new ArrayList<Kitabe>();
    ArrayList<Kitabe> returned = new ArrayList<Kitabe>();
    Member(String name , int age , String phone , long paid_fine, long due_fine ){
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.paid_fine = paid_fine;
        this.due_fine = due_fine;


    }

}

//----------------DATA STRUCTURE FOR BOOKS
class Kitabe{
    String book_name;
    String author;
    int copies;
    int bookid;

    int issuestage;
    LocalDateTime currtime;

    Kitabe(String bookname , int id,String athor , int copy ,int issuestage, LocalDateTime curr){
        this.book_name = bookname;
        this.bookid = id;
        this.author = athor;
        this.copies = copy;
        this.currtime = curr;
        this.issuestage = issuestage;
    }
}


//-------------BOOK SELVES
class Booklist {
    ArrayList<Kitabe> book_arrays = new ArrayList<Kitabe>();
    int total_id = 1;

    public void add(String bookname, String author, int copies) {
        LocalDateTime curr = null;
        int issuestage = 0;
        for (int i = 0; i < copies; i++) {
            Kitabe kitab1 = new Kitabe(bookname, total_id, author, copies,issuestage,curr);
            book_arrays.add(kitab1);
            total_id++;
        }

    }
    public void printlist() {
        System.out.println("Available Books: ");
        for (Kitabe bookArray : book_arrays) {
            System.out.println("Book title: "+ bookArray.book_name +" Author: "+bookArray.author+ " Id: "+ bookArray.bookid );
        }
        System.out.println("-----------------------------");

    }

    public void remove(int book_id) {
        for (int i = 0; i < book_arrays.size(); i++) {
            if (book_arrays.get(i).bookid == book_id) {
                book_arrays.remove(i);
                System.out.println("Book removed successfully!!");
                System.out.println("-----------------------------");
                return ;
            }
        }
        System.out.println("NO book with "+ book_id + " id exist");
        System.out.println("-----------------------------");
    }
}

class librarian  {

    private ArrayList<Member> member_array = new ArrayList<Member>();

    Scanner input2 = new Scanner(System.in);
    private Booklist book = new Booklist();
    private void add_book(){

        /*-----------------------------INPUT ---------------------------------------*/
        Scanner input2 = new Scanner(System.in);
        System.out.print("Book title: ");
        String title = input2.nextLine();
        System.out.print("Author: ");
        String author = input2.nextLine();
        System.out.print("Copies: ");
        int copies = input2.nextInt();

        /*--------------------------STUFF ---------------------*/
        this.book.add(title, author , copies);

    }

    public void view_all_books(){
        this.book.printlist();

    }
    //-----------METHODS DEFINING FOR MEMBER INTERFACE AS MEMBER HAVE NO ACCESS TO MEMBER LIST
    public int existence(String name , String phone){
        for (int i = 0 ; i < this.member_array.size() ; i++){
            if(this.member_array.get(i).name.equals(name) && this.member_array.get(i).phone.equals(phone)){
                return i;
            }
        }
        return -1;
    }

    public void view_my_books(String name, String phone){
        for (Member member : this.member_array){
            if(member.name.equals(name) && member.phone.equals(phone)){
                if(member.returned.isEmpty() && member.borrowed.isEmpty()){
                    System.out.println("Currently , you are not holding any book");
                    System.out.println("-----------------------------");
                    return;
                }
                if(!member.borrowed.isEmpty()) {
                    System.out.println("Currently issued books: ");
                    for (Kitabe books : member.borrowed) {
                        System.out.println("Book Name: " + books.book_name + " Book ID: " + books.bookid);
                    }
                }
                if(!member.returned.isEmpty()) {
                    System.out.println("Currently returned books: ");
                    for (Kitabe books : member.returned) {
                        System.out.println("Book Name: " + books.book_name + " Book ID: " + books.bookid);
                    }
                }
                System.out.println("-----------------------------");
                break;
            }
        }
    }
    private void remove_book(){
        System.out.print("Enter book id to remove: ");
        int id = input2.nextInt();
        this.book.remove(id);
    }

    private void register_mem(){
        Scanner input2 = new Scanner(System.in);
        System.out.print("Name: ");
        String name = input2.nextLine() ;
        System.out.print("Age: ");
        int age = -1;
        try {
            age = input2.nextInt();
        }
        catch (Exception e){
            System.out.println("Invalid age");
            System.out.println("-----------------------------");
            return;
        }
        if(age < 0 || age > 100){
            System.out.println("Invalid age");
            System.out.println("-----------------------------");
            return ;
        }
        System.out.print("Phone no. : ");
        String phone = input2.next();
        if (!phone.matches("\\d+")) {
            System.out.println("Invalid phone number. Please enter numeric characters only.");
            System.out.println("-----------------------------");
            return;
        }

        if(phone.length() != 10){
            System.out.println("Wrong phone number!!");
            System.out.println("-----------------------------");
            return;
        }
        for (int i = 0 ; i <this.member_array.size();i++ ){
            if(this.member_array.get(i).phone.equals(phone)){
                System.out.println("Member with Same Phone No. Already exist!!");
                System.out.println("-----------------------------");
                return;
            }
        }
        long paid_fine = 0;
        long due_fine = 0;
        LocalDateTime curr_time = null;
        Member person = new Member(name, age , phone , paid_fine , due_fine);
        this.member_array.add(person);
        System.out.println("Member Added Succesfully!!");
        System.out.println("-----------------------------");
    }
    public void view_member(){
        for (Member member : this.member_array) {
            System.out.println("Name: "+ member.name+ " Phone: "+ member.phone+ " Fine: "+ member.due_fine);
        }
        System.out.println("-----------------------------");

    }
    private void remove_member(){
        Scanner input2 = new Scanner(System.in);
        System.out.print("Name : ");
        String name = input2.nextLine();
        System.out.print("Phone : ");
        String phone = input2.next();
        if (!phone.matches("\\d+")) {
            System.out.println("Invalid phone number. Please enter numeric characters only.");
            System.out.println("-----------------------------");
            return;
        }

        if(phone.length() != 10){
            System.out.println("Wrong phone number!!");
            System.out.println("-----------------------------");
            return;
        }
        for (int i = 0 ; i < this.member_array.size() ; i++){
            if(this.member_array.get(i).name.equals(name)  && this.member_array.get(i).phone.equals(phone)){
                for (int j = this.member_array.get(i).borrowed.size() - 1 ; j >= 0 ; j--){
                    this.book.book_arrays.add(this.member_array.get(i).borrowed.get(j));
                    this.member_array.get(i).borrowed.remove(j);
                }
                this.member_array.remove(i);
                System.out.println("Member removed Successfully !!");
                System.out.println("-----------------------------");
                return ;
            }
        }
        System.out.println("Member doesn't exist");
        System.out.println("-----------------------------");

    }

    public void calculate_fine(String name , String phone ){
        for (Member member: this.member_array){
            if(member.name.equals(name) && member.phone.equals(phone)){
                LocalDateTime curr = LocalDateTime.now();
                long totalfine = 0;
                if(member.borrowed.isEmpty()){
                    return;
                }
                for (int i = 0 ; i < member.borrowed.size();i++){
                    Duration timeDifference = Duration.between(member.borrowed.get(i).currtime, curr);
                    long hours = timeDifference.toHours();
                    long minutes = timeDifference.toMinutesPart();
                    long seconds = timeDifference.toSecondsPart();
                    long totalseconds = (hours * 60 * 60 )+ (minutes * 60 )+ seconds;
                    if(totalseconds < 10 && member.borrowed.get(i).issuestage == 0){
                        continue;
                    }
                    if(totalseconds >= 10 && member.borrowed.get(i).issuestage == 0){
                        totalfine +=( totalseconds - 10 ) * 3;
                        System.out.println("1. Totalsecons "+ totalseconds);
                        LocalDateTime a = LocalDateTime.of(curr.getYear(), curr.getMonth(), curr.getDayOfMonth(), curr.getHour(), curr.getMinute(),curr.getSecond()-10);
                        member.borrowed.get(i).currtime = a;
                        member.borrowed.get(i).issuestage = 1;
                    }
                    else {
                        System.out.println("2. Totalsecons "+ totalseconds);
                        totalfine += totalseconds * 3;
                    }

                }
                member.due_fine = totalfine;
                System.out.println("Total due fine is "+ member.due_fine);
                return;
            }
        }

    }

    public void issue(String name ,String phone ){              // checking of member exist everywhere is necessary
        calculate_fine(name , phone);
        for (in t j = 0; j < this.member_array.size();j++ ){
            if(this.member_array.get(j).name.equals(name) && this.member_array.get(j).phone.equals(phone)){
                if(this.member_array.get(j).due_fine != 0){
                    System.out.println("Cannot issue book. You have Pending Fine of Rs. "+ this.member_array.get(j).due_fine);
                    System.out.println("-----------------------------");
                    return ;
                }
                else if(this.member_array.get(j).borrowed.size() > 1){
                    System.out.println("Cannot issue more than 2 book.");
                    System.out.println("-----------------------------");
                    return ;
                }
                else {
                    Scanner input3= new Scanner(System.in);
                    System.out.print("Book Id: ");
                    int id = -1;
                    try{
                        id = input3.nextInt();
                    }
                    catch (Exception e){
                        System.out.println("Invalid id");
                        System.out.println("-----------------------------");
                        return ;
                    }
                    if (id !=-1) {
                        for (int k = 0 ; k < this.book.book_arrays.size();k++){
                            if(this.book.book_arrays.get(k).bookid == id){
                                this.book.book_arrays.get(k).currtime = LocalDateTime.now();
                                this.book.book_arrays.get(k).issuestage = 0;
                                this.member_array.get(j).borrowed.add(this.book.book_arrays.get(k));
                                this.book.book_arrays.remove(k);
                                return ;
                            }
                        }
                    }
                }
            }
        }
    }

    public void payfine(String name ,String phone){
        calculate_fine(name, phone);
        for (int i = 0 ; i < this.member_array.size(); i++){
            if(this.member_array.get(i).name.equals(name) && this.member_array.get(i).phone.equals(phone)){
                if(this.member_array.get(i).due_fine > 0){
                    System.out.println("You have fine of Rs. "+ this.member_array.get(i).due_fine);
                    this.member_array.get(i).paid_fine += this.member_array.get(i).due_fine;
                    for (int j = 0 ; j < this.member_array.get(i).borrowed.size();j++ ){
                        this.member_array.get(i).borrowed.get(j).currtime = LocalDateTime.now();
                    }
                    this.member_array.get(i).due_fine = 0;
                    System.out.println("Fine Paid Successfully!!");
                    System.out.println("-----------------------------");
                    return ;
                }
                else{
                    System.out.println("You have no fine pending");
                    System.out.println("-----------------------------");
                    return ;
                }
            }
        }
    }
    public void returnbook(String name , String phone){
        for (int i = 0 ; i < this.member_array.size(); i++){
            if (this.member_array.get(i).name.equals(name) && this.member_array.get(i).phone.equals(phone)){
                if (this.member_array.get(i).borrowed.isEmpty()){
                    System.out.println("No Borrowed Books");
                    System.out.println("-----------------------------");
                    return ;
                }
                System.out.println("Borrowed Books: ");
                for (int j = 0 ; j < this.member_array.get(i).borrowed.size();j++){
                    System.out.println("Book Name: "+ this.member_array.get(i).borrowed.get(j).book_name + " Book Id:" + this.member_array.get(i).borrowed.get(j).bookid );
                }
                System.out.println("-----------------------------");
                System.out.println("Book ID: ");
                Scanner input3 = new Scanner(System.in);
                int id = -1;
                try {
                    id = input3.nextInt();
                }
                catch (Exception e){
                    System.out.println("Invalid id");
                    System.out.println("-----------------------------");
                    return ;
                }
                for (int j = 0 ; j < this.member_array.get(i).borrowed.size();j++){
                    if(this.member_array.get(i).borrowed.get(j).bookid == id){
                        calculate_fine(name,phone);
                        System.out.println("Currently you have fine of Rs. "+ this.member_array.get(i).due_fine);
                        this.member_array.get(i).borrowed.get(j).currtime = null;
                        this.member_array.get(i).borrowed.get(j).issuestage= 0;
                        this.book.book_arrays.add(this.member_array.get(i).borrowed.get(j));
                        this.member_array.get(i).returned.add(this.member_array.get(i).borrowed.get(j));
                        this.member_array.get(i).borrowed.remove(j);
                        System.out.println("Book Returned Successfully!!");
                        System.out.println("-----------------------------");
                        return ;
                    }
                }
                System.out.println("Incorrect Book Id");
                System.out.println("-----------------------------");
            }
        }
    }
    public int get_input(){
        while(true) {
            System.out.println("1. Register a member");
            System.out.println("2. Remove a member");
            System.out.println("3. Add a book");
            System.out.println("4. Remove a book");
            System.out.println("5. View all members along with their books and fines to be paid ");
            System.out.println("6. View all books");
            System.out.println("7. Back");
            System.out.println("-----------------------------");
            int inp2 = input2.nextInt();
            if (inp2 == 1) {
                this.register_mem();
            } else if (inp2 == 2) {
                this.remove_member();
            } else if (inp2 == 3) {
                this.add_book();
            } else if (inp2 == 4) {
                this.remove_book();
            } else if (inp2 == 5) {
                this.view_member();
            } else if (inp2 == 6) {
                this.view_all_books();
            } else if (inp2 == 7) {
                return 1;
            }
        }
    }


}


//-----------------INTERFACE FOR MEMBER

class Memberinterface  {

    String name ;
    String phone;
    int member_id ;
    public void my_details(){
        System.out.println(name);
        System.out.println(phone);
        System.out.println("-----------------------------");
    }

    public int get_input2(librarian lib ){
        Scanner input3 = new Scanner(System.in);
        while(true) {
            System.out.println("1. List Available Books");
            System.out.println("2. List My Books");
            System.out.println("3. Issue book");
            System.out.println("4. Return book");
            System.out.println("5. Pay Fine");
            System.out.println("6. Back");
            System.out.println("-----------------------------");

            int inp1 = input3.nextInt();
            if (inp1 > 6 || inp1 < 0) {
                System.out.println("Invalid command");
                System.out.println("-----------------------------");
                continue;
            }
            if (inp1 == 1) {
                lib.view_all_books();
            }
            else if (inp1 == 2) {
                lib.view_my_books(name,phone);
            }
            else if(inp1 == 3){
                lib.issue(name, phone);
            }
            else if(inp1 == 4){
                lib.returnbook(name, phone);
            }
            else if(inp1 == 5){
                lib.payfine(name, phone);
            }
            else {
                return 1;
            }
        }
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        librarian lib = new librarian();
        Memberinterface mem = new Memberinterface();
        while(true){
            System.out.println("1. Enter as a Librarian");
            System.out.println("2. Enter as a Member");
            System.out.println("3. Exit");
            System.out.println("-----------------------------");
            int inp1 = input.nextInt();
            if (inp1 > 3 || inp1 < 1){
                System.out.println("Invalid command");
                System.out.println("-----------------------------");
                continue;
            }
            else {
                if (inp1 == 1){
                    int check = lib.get_input();
                    if(check == 1){
                        continue;
                    }
                }
                else if (inp1 == 2){
                    Scanner input2 = new Scanner(System.in);
                    System.out.print("Name: ");
                    String nam = input2.nextLine();
                    System.out.print("Phone no.: ");
                    String phone = input2.next();
                    if (!phone.matches("\\d+")) {
                        System.out.println("Invalid phone number. Please enter numeric characters only.");
                        System.out.println("-----------------------------");
                        return;
                    }

                    if(phone.length() != 10){
                        System.out.println("Wrong phone number!!");
                        System.out.println("-----------------------------");
                        return;
                    }
                    int indx = lib.existence(nam,phone);
                    if(indx != -1){
                        mem.name = nam;
                        mem.phone = phone;
                        System.out.println("Welcome "+nam+".Member ID: "+phone);
                        System.out.println("-----------------------------");
                        int check2 = mem.get_input2(lib );
                        if(check2 == 1){
                            continue;
                        }
                    }
                    else{
                        System.out.println("Member with Name: "+ nam+ " and Phone No.: "+ phone + " doesn't exist. ");
                        System.out.println("-----------------------------");
                        continue;
                    }
                }
                else{
                    break;
                }
            }
        }

    }
}
