package Task2;

import java.util.*;

public class HomeWork {
    public static void main(String[] args) {
        SalaryEmployee mySalaryEmployee = new SalaryEmployee(null, "Анатолий", 4500, 201);
        System.out.println(mySalaryEmployee);

        Freelancer myFreelancer = new Freelancer("Петров", "Николай", 400, 199);
        System.out.println(myFreelancer);

        Employee employee = EmployeeFabric.generateEmployee();
        System.out.println(employee);

        System.out.println("-------------");
        Employee[] workers = EmployeeFabric.generateManyEmployees(20);

        Arrays.sort(workers);
        for (Employee worker : workers) System.out.println(worker);

        System.out.println("-------------");
        Arrays.sort(workers, new SalaryComparator());
        for (Employee worker : workers) System.out.println(worker);
    }
}

abstract class Employee implements Comparable<Employee> {
    protected String surname;
    protected String name;
    protected double rate;
    protected Integer employeeCode = 0;
    protected static Set<Integer> employeeCodes = new HashSet<>();
    private static Random random = new Random();

    public Employee(String surname, String name, double rate, Integer employeeCode) {
        setSurname(surname);
        setName(name);
        setRate(rate);

        if (employeeCode <= 0 || employeeCodes.contains(employeeCode)) this.employeeCode = random.nextInt(1, 1000000000);
        else this.employeeCode = employeeCode;

        employeeCodes.add(employeeCode);
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (Objects.equals(surname, "") || surname == null) {
            this.surname = "Фамилия";
        }
        else this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (Objects.equals(name, "") || name == null) this.name = "Имя";
        else this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        if (rate <= 0) this.rate = 300;
        else this.rate = rate;
    }

    public Integer getEmployeeCode() {
        return employeeCode;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return String.format("Код: %s,\nФамилия: %s,\nИмя: %s,\nСтавка: %.2f руб.,\nЗарплата в месяц: %.2f руб.\n",
                employeeCode, surname, name, rate, calculateSalary());
    }

    @Override
    public int compareTo(Employee o) {
        return employeeCode.compareTo(o.employeeCode);
    }
}

class SalaryEmployee extends Employee {
    private final double MROT = 16242.00;

    public SalaryEmployee(String surname, String name, double rate, Integer employeeCode) {
        super(surname, name, rate, employeeCode);
    }

    public double getMROT() {
        return MROT;
    }

    @Override
    public double calculateSalary() {
        return Math.max(rate, MROT);
    }

    @Override
    public String toString() {
        return String.format("Сотрудник в штате:\nКод: %s, \nФамилия: %s;\nИмя: %s;\nЗарплата в месяц: %.2f руб.\n",
                employeeCode, surname, name, calculateSalary());
    }

}

class Freelancer extends Employee {

    public Freelancer(String surname, String name, double rate, Integer employeeCode) {
        super(surname, name, rate, employeeCode);
    }

    @Override
    public double calculateSalary() {
        return 20.8 * 8 * rate;
    }

    @Override
    public String toString() {
        return "Подрядчик:\n" + super.toString();
    }
}

abstract class EmployeeFabric {
    private static Random random = new Random();

    public static Employee generateEmployee() {
        String[] names = new String[] {"Анатолий", "Виктор", "Дмитрий", "Антон", "Глеб", "Игорь", "Владислав"};
        String[] surnames = new String[] {"Осипов", "Афонин", "Наземнов", "Григорьев", "Астафьев", "Шестаков",
                "Сидоров"};

        int rate = random.nextInt(0, 500000);
        int employeeCode = random.nextInt(1, 1000000000);

        if (rate % 2 == 0) return new SalaryEmployee(
                surnames[random.nextInt(surnames.length)],
                names[random.nextInt(names.length)], rate, employeeCode);

        return new Freelancer(
                surnames[random.nextInt(surnames.length)],
                names[random.nextInt(names.length)], rate, employeeCode);
    }

    public static Employee[] generateManyEmployees(int quantity) {
        Employee[] workers = new Employee[quantity];
        for (int i = 0; i < quantity; i++) {
            workers[i] = generateEmployee();
        }

        return workers;
    }
}

class SalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Double.compare(o2.calculateSalary(), o1.calculateSalary());
    }
}

