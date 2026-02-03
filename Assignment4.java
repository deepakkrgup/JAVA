import java.util.*;
import java.util.stream.*;
class Employee {
    int id;
    String name;
    String dept;
    int salary;
    List<String> skills;

    Employee(int id, String name, String dept, int salary, List<String> skills) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.salary = salary;
        this.skills = skills;
    }

    @Override
    public String toString() {
        return name + " | " + dept + " | " + salary;
    }
}

public class Assignment4 {

    public static void main(String[] args) {

        List<Employee> employees = List.of(
            new Employee(1, "Aman", "IT", 70000, List.of("Java", "Spring")),
            new Employee(2, "Ravi", "HR", 40000, List.of("Recruitment")),
            new Employee(3, "Neha", "IT", 90000, List.of("Java", "Docker")),
            new Employee(4, "Pooja", "Finance", 60000, List.of("Excel", "Accounts")),
            new Employee(5, "Aman", "IT", 70000, List.of("Java", "Spring"))
        );

        List<Employee> highSalary =
                employees.stream()
                         .filter(e -> e.salary > 60000)
                         .toList();

        List<String> names =
                employees.stream()
                         .map(e -> e.name)
                         .toList();

        Set<String> uniqueNames =
                employees.stream()
                         .map(e -> e.name)
                         .collect(Collectors.toSet());

        List<Employee> sortedBySalaryDesc =
                employees.stream()
                         .sorted((a, b) -> b.salary - a.salary)
                         .toList();

        List<Employee> secondThirdHighest =
                employees.stream()
                         .sorted((a, b) -> b.salary - a.salary)
                         .skip(1)
                         .limit(2)
                         .toList();

        Set<String> uniqueSkills =
                employees.stream()
                         .flatMap(e -> e.skills.stream())
                         .collect(Collectors.toSet());

        int totalSalary =
                employees.stream()
                         .map(e -> e.salary)
                         .reduce(0, Integer::sum);

        double averageSalary =
                employees.stream()
                         .map(e -> e.salary)
                         .reduce(Integer::sum)
                         .map(sum -> sum / (double) employees.size())
                         .orElse(0.0);

    
        Map<String, List<Employee>> byDepartment =
                employees.stream()
                         .collect(Collectors.groupingBy(e -> e.dept));

       
        Map<String, Employee> highestPaidByDept =
                employees.stream()
                         .collect(Collectors.groupingBy(
                             e -> e.dept,
                             Collectors.collectingAndThen(
                                 Collectors.maxBy(Comparator.comparingInt(e -> e.salary)),
                                 Optional::get
                             )
                         ));

        
        List<String> topSkills =
                employees.stream()
                         .filter(e -> e.dept.equals("IT"))
                         .filter(e -> e.salary > 60000)
                         .flatMap(e -> e.skills.stream())
                         .distinct()
                         .sorted()
                         .limit(3)
                         .toList();

        
        Map<String, Map<String, Double>> departmentReport =
                employees.stream()
                         .collect(Collectors.groupingBy(
                             e -> e.dept,
                             Collectors.collectingAndThen(
                                 Collectors.toList(),
                                 list -> {
                                     double total = list.stream()
                                                        .map(e -> e.salary)
                                                        .reduce(0, Integer::sum);

                                     double avg = total / list.size();

                                     return Map.of(
                                         "total", total,
                                         "average", avg,
                                         "count", (double) list.size()
                                     );
                                 }
                             )
                         ));

        
        System.out.println("Task 1: " + highSalary);
        System.out.println("Task 2: " + names);
        System.out.println("Task 3: " + uniqueNames);
        System.out.println("Task 4: " + sortedBySalaryDesc);
        System.out.println("Task 5: " + secondThirdHighest);
        System.out.println("Task 6: " + uniqueSkills);
        System.out.println("Task 7: " + totalSalary);
        System.out.println("Task 8: " + averageSalary);
        System.out.println("Task 9: " + byDepartment);
        System.out.println("Task 10: " + highestPaidByDept);
        System.out.println("Task 11: " + topSkills);
        System.out.println("Task 12: " + departmentReport);
    }
}
