import java.util.Scanner;
import java.util.ArrayList;
public class company_emp {
    private Scanner sw = new Scanner(System.in);
    private ArrayList<employees> employeeList = new ArrayList<>();

    abstract class employees {
        private String first_name;
        private String last_name;
        private String ss_num;
        private String type_con;

        public employees(String first_name, String last_name, String ss_num, String type_con) {
            this.first_name = first_name;
            this.last_name = last_name;
            this.ss_num = ss_num;
            this.type_con = type_con;
        }

        public abstract double cal_income();

        public String get_first_name() {
            return first_name;
        }

        public String get_last_name() {
            return last_name;
        }

        public String get_ss_num() {
            return ss_num;
        }

        public String get_type_con() {
            return type_con;
        }

        @Override
        public String toString() {
            return "first_name: " + first_name + "\nLast_name: " + last_name + "\nSocial_security_number: " + ss_num
                    + "\ncontract_type: " + type_con;
        }
    }

    class full_time_emp extends employees {
        private double weekly_salary;

        public full_time_emp(String first_name, String last_name, String ss_num, String type_con, int weekly_salary) {
            super(first_name, last_name, ss_num, type_con);
            this.weekly_salary = weekly_salary;
        }

        @Override
        public double cal_income() {
            return weekly_salary;
        }

        @Override
        public String toString() {
            return super.toString() + "\nposition: Full time" + "\nweekly_salary: " + cal_income();
        }
    }

    class part_time_emp extends employees {
        private int hours_working;
        private int hourly_rate;

        public part_time_emp(String first_name, String last_name, String ss_num, String type_con, int hours_working,
                int hourly_rate) {
            super(first_name, last_name, ss_num, type_con);
            this.hours_working = hours_working;
            this.hourly_rate = hourly_rate;
        }

        @Override
        public double cal_income() {
            return hours_working * hourly_rate;
        }

        @Override
        public String toString() {
            return super.toString() + "\nposition: Part time" + "\nhours_working: " + hours_working + "\nhourly_rate: "
                    + hourly_rate + "\nweekly_salary: " + cal_income();
        }
    }

    class commission_emp extends employees {
        private int number_of_sales;
        private double commission_percentage;
        private double price_of_each_sale;

        public commission_emp(String first_name, String last_name, String ss_num, String type_con, int number_of_sales,
                double commission_percentage, double price_of_each_sale) {
            super(first_name, last_name, ss_num, type_con);
            this.number_of_sales = number_of_sales;
            this.commission_percentage = commission_percentage;
            this.price_of_each_sale = price_of_each_sale;
        }

        @Override
        public double cal_income() {
            double tmp = number_of_sales * price_of_each_sale;
            return (commission_percentage / 100) * tmp;
        }

        @Override
        public String toString() {
            return super.toString() + "\npostion: Commission Based" + "\nnumber of sales: " + number_of_sales
                    + "\ncommission percentage: " + commission_percentage + "%" + "\nPrice per sale: " + price_of_each_sale
                    + "\nweekly salary: " + cal_income();
        }
    }

    class base_commission_emp extends commission_emp {
        private double base_salary;

        public base_commission_emp(String first_name, String last_name, String ss_num, String type_con,
                double base_salary, int number_of_sales, double commission_percentage, double price_of_each_sale) {
            super(first_name, last_name, ss_num, type_con, number_of_sales, commission_percentage, price_of_each_sale);
            this.base_salary = base_salary;
        }

        @Override
        public double cal_income() {
            double tmp = super.cal_income();
            return tmp + base_salary + (10 / 100.0 * base_salary);
        }

        @Override
        public String toString() {
            return super.toString() + "\nBase salary: " + base_salary + "\nWeekly salary: " + cal_income();
        }
    }

    public void reg_emp() {
        System.out.println("  -----------------------------------------------" + "\n  -----------------------------------------------");
        System.out.println("|       1.Full Time Employee                 |");
        System.out.println("|       2.Part Time Employee                 |");
        System.out.println("|       3.Commission Employee                |");
        System.out.println("|       4.Base Commision Employee            |");
        System.out.println("  -----------------------------------------------" + "\n  -----------------------------------------------");
        System.out.println("Choose type: ");
        System.out.print("--> ");
        int type = sw.nextInt();
        sw.nextLine();
        System.out.print("First name: ");
        String f_na = sw.nextLine();
        System.out.print("Last name: ");
        String l_na = sw.nextLine();
        System.out.print("Social Security Number: ");
        String ssn = sw.nextLine();
        System.out.print("Type of contract: ");
        String tycon = sw.nextLine();

        switch (type) {
            case 1:
                System.out.print("Weekly salary: ");
                int wsal = sw.nextInt();
                sw.nextLine();
                employeeList.add(new full_time_emp(f_na, l_na, ssn, tycon, wsal));
                System.out.println("Employee registered successfully");
                break;

            case 2:
                System.out.print("Working hours: ");
                int wh = sw.nextInt();
                System.out.print("Hourly rate: ");
                int hr = sw.nextInt();
                sw.nextLine();
                employeeList.add(new part_time_emp(f_na, l_na, ssn, tycon, wh, hr));
                System.out.println("Employee registered successfully");
                break;

            case 3:
                System.out.print("Number of sales: ");
                int sales = sw.nextInt();
                System.out.print("Commission percentage: ");
                double commission = sw.nextDouble();
                System.out.print("Price per sale: ");
                double price = sw.nextDouble();
                sw.nextLine();
                employeeList.add(new commission_emp(f_na, l_na, ssn, tycon, sales, commission, price));
                System.out.println("Employee registered successfully");
                break;

            case 4:
                System.out.print("Base salary: ");
                double baseSalary = sw.nextDouble();
                System.out.print("Number of sales: ");
                int baseSales = sw.nextInt();
                System.out.print("Commission percentage: ");
                double baseCommission = sw.nextDouble();
                System.out.print("Price per sale: ");
                double basePrice = sw.nextDouble();
                sw.nextLine();
                employeeList.add(new base_commission_emp(f_na, l_na, ssn, tycon, baseSalary, baseSales, baseCommission,
                        basePrice));
                System.out.println("Employee registered successfully");
                break;

            default:
                System.out.println("Invalid!! Please try again.");
                break;
        }
    }

    public void print_emp_data() {
        for (employees x : employeeList) {
            System.out.println("  -----------------------------------------------");
            System.out.println(x.toString());
            System.out.println("  -----------------------------------------------");
        }
    }

    public void print_emp_position() {
        for (employees x : employeeList) {
            String pos = "null";
            if (x instanceof full_time_emp) {
                pos = "Full-time";
            } else if (x instanceof part_time_emp) {
                pos = "Part-Time";
            } else if (x instanceof commission_emp) {
                pos = "Commission Employee";
            } else if (x instanceof base_commission_emp) {
                pos = "Base commission Employee";
            } else {
                System.out.println("Invalid employee type found");
            }
            System.out.println("  -----------------------------------------------");
            System.out.println("First Name: " + x.get_first_name() + " " + "Last Name: " + x.get_last_name() + ": " + pos);
            System.out.println("  -----------------------------------------------");
        }
    }

    public void print_emp_contract() {
        for (employees x : employeeList) {
            System.out.println("  -----------------------------------------------");
            System.out.println(x.get_first_name() + " " + x.get_last_name() + ": " + x.get_type_con());
            System.out.println("  -----------------------------------------------");
        }
    }

    public void main_menu() {
        String ans;
        do {
            System.out.println("  -----------------------------------------------" + "\n  -----------------------------------------------");
            System.out.println("|       Choose from the menu                   |");
            System.out.println("  -----------------------------------------------" + "\n  -----------------------------------------------");
            System.out.println("|       1. Register employees                  |");
            System.out.println("|       2. Print employee data                 |");
            System.out.println("|       3. Print by position                   |");
            System.out.println("|       4. Print by contract                   |");
            System.out.println("|       5. Exit                                |");
            System.out.println("  -----------------------------------------------" + "\n  -----------------------------------------------");

            System.out.print("Choose an operation: ");
            int choice = sw.nextInt();
            sw.nextLine();

            switch (choice) {
                case 1:
                    reg_emp();
                    break;
                case 2:
                    print_emp_data();
                    break;
                case 3:
                    print_emp_position();
                    break;
                case 4:
                    print_emp_contract();
                    break;
                case 5:
                    System.out.println("Thank you for using our software!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }

            System.out.print("Do you want to proceed (y/n): ");
            ans = sw.nextLine();
        } while (ans.equalsIgnoreCase("y"));
    }

    public static void main(String[] args) {
        company_emp company = new company_emp();
        company.main_menu();
    }
}
