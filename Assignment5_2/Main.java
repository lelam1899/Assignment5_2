package Assignment5_2;
import java.sql.*;
import java.util.Scanner;

public class Main {

    final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final static String JDBC_URL = "jdbc:mysql://localhost:3306/t1806e";
    final static String JDBC_USER = "t1806e";
    final static String JDBC_PASS = "lam1806e";

    public static void main(String args[]){

        try {
            Class.forName(Main.JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(Main.JDBC_URL,
                    Main.JDBC_USER,Main.JDBC_PASS);
            Statement statement = conn.createStatement();
            Main.showMenu(statement);

        }catch (ClassNotFoundException e){

        }catch (SQLException e){

        }
    }

    public static void showMenu(Statement statement){

        Scanner scanner = new Scanner(System.in);
        int menu = 0;
        boolean flag = true;
        do {
            do{
                System.out.println("\nLua chon chuc nang: ");
                System.out.println("1. Dang nhap");
                System.out.println("2. Dang ky");
                System.out.println("3. Danh sach nguoi dung");
                System.out.println("4. Xoa 1 nguoi dung");
                System.out.println("5. Thoat chuong trinh");
                menu = scanner.nextInt();
            }while (menu <1 || menu > 5);

            switch (menu){
                case 1: Main.login(statement);break;
                case 2: Main.register(statement);break;
                case 3: Main.ShowList(statement);break;
                case 4: Main.delete(statement);break;
                case 5: flag=false; break;
            }
        }while (flag == true);


    }

    public static boolean login(Statement statement){
        boolean flag = true;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap username:");
        String username = scanner.nextLine();
        System.out.println("Nhap password:");
        String password = scanner.nextLine();

        String sql = "SELECT id FROM user WHERE username LIKE '"
                +username+"' AND password LIKE '"+password+"'";

        try{
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                System.out.println("Dang nhap thanh cong.");
                return flag;
            }
            flag = false;
        }catch (SQLException e){

        }

        System.out.println("Khong tim thay nguoi dung");
        return flag;
    }


    public static void register(Statement statement){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap username:");
        String username = scanner.nextLine();
        System.out.println("Nhap email:");
        String email = scanner.nextLine();
        System.out.println("Nhap password:");
        String password = scanner.nextLine();

        String sql = "INSERT INTO user (username,email,password) VALUES('"+username
                +"','"+email+"','"+password+"')";
        try {
            statement.executeUpdate(sql);
            System.out.println("Dang ky thanh cong.");
            return;
        }catch (SQLException e){

        }
        System.out.println("Dang ky that bai");
    }

    public static void ShowList(Statement statement) {

        try {
            System.out.println("Danh sach: ");
            String sql1 = "SELECT * FROM user";
            statement.executeQuery(sql1);
            ResultSet resultSet = statement.executeQuery(sql1);
            while (resultSet.next()){
                System.out.println("ID: "+resultSet.getInt("id"));
                System.out.println("Name: "+resultSet.getString("username"));
                System.out.println("Email: "+resultSet.getString("email"));
                System.out.println("Password: "+resultSet.getString("password"));
                System.out.println("Status: "+resultSet.getInt("status"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void delete(Statement statement){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap ID: ");
        int id = scanner.nextInt();
        String sql2 = "DELETE FROM user WHERE id=" +id;
        try {
            statement.executeUpdate(sql2);
            System.out.println("Xoa thanh cong!");
            return;
        }catch (SQLException e){

        }
        System.out.println("ID khong ton tai!");


    }
}
