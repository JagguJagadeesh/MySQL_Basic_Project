import java.sql.*;
import java.util.Scanner;

public class Student_Management_System {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/mydb";
    private static final String user = "root";
    private static final String password = "Jaggu20";

    public static void main(String[] args) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner sc = new Scanner(System.in);



        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("--------------Connected to System----------------");


            while (true) {
                System.out.println("------------------Student Management System-----------------");
                System.out.println("1. Add Student\n2. Edit Student Detailes\n3. Delete Student\n4. View Student Details\n5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        AddStudent(sc,con);
                        break;
                    }
                    case 2 -> {
                        EditStudent(sc,con);
                        break;
                    }
                    case 3 -> {
                        DeleteStudent(sc,con);
                        break;
                    }
                    case 4 -> {
                        ViewStudents(sc,con);
                        break;
                    }
                    case 5 -> {
                        sc.close();
                        con.close();
                        System.out.println("--------Connection closed--------");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid choice");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    private static void AddStudent(Scanner sc,Connection con) throws SQLException {
        System.out.println("\tAdding Student");
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        System.out.print("Enter Student Name: ");
        String name = sc.next();
        System.out.print("Enter Student Gender: ");
        String gender = sc.next();
        System.out.print("Enter Student Class: ");
        String sclass  = sc.next();
        System.out.print("Enter Student Age: ");
        int age = sc.nextInt();
        String qurey = "insert into student_management_system() values (?,?,?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(qurey);
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,name);
        preparedStatement.setInt(3,age);
        preparedStatement.setString(4,sclass);
        preparedStatement.setString(5,gender);
        int rowsAffected = preparedStatement.executeUpdate();
        if(rowsAffected>0){
            System.out.println(name+" Added Sucessfully....");
        }
        else {
            System.out.println("Adding Student Failed!....");
        }

    }
    private static void EditStudent(Scanner sc,Connection con) throws SQLException {
        System.out.println("------------Update Student Detailes----------------");
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        System.out.print("Enter Student Name: ");
        String name = sc.next();
        System.out.print("Enter Student Gender: ");
        String gender = sc.next();
        System.out.print("Enter Student Class: ");
        String sclass  = sc.next();
        System.out.print("Enter Student Age: ");
        int age = sc.nextInt();
        String query = "update student_management_system set student_name = ?, student_gender = ?, student_age = ?, student_class = ? where student_id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,gender);
        preparedStatement.setInt(3,age);
        preparedStatement.setString(4,sclass);
        preparedStatement.setInt(5,id);
        int rowsAffected = preparedStatement.executeUpdate();
        if(rowsAffected>0){
            System.out.println("Student "+id+" is Updated Sucessfully....");
        }else{
            System.out.println("Update Student Failed!....");
        }

    }
    private static void DeleteStudent(Scanner sc,Connection con) throws SQLException {
        System.out.println("--------------Deleting Student--------------");
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        String query = "delete from student_management_system where student_id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1,id);
        int rowsAffected = preparedStatement.executeUpdate();
        if(rowsAffected>0){
            System.out.println("Student "+id+" is Deleted Sucessfully....");
        }
        else {
            System.out.println("Deleting Student Failed!....");
        }

    }
    private static void ViewStudents(Scanner sc,Connection con) throws SQLException {
        System.out.println("---------------View Student----------------");
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        String query = "select * from student_management_system where student_id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println("|--------------------------------------------------------|");
            System.out.println("\tStudent ID     :    "+resultSet.getString("student_id"));
            System.out.println("\tStudent Name   :    "+resultSet.getString("student_name"));
            System.out.println("\tStudent Gender :    "+resultSet.getString("student_gender"));
            System.out.println("\tStudent Class  :    "+resultSet.getString("student_class"));
            System.out.println("\tStudent Age    :    "+resultSet.getString("student_age"));
            System.out.println("|---------------------------------------------------------|");
        }
    }

}
