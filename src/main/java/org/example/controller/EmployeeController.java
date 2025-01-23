package org.example.controller;


import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmployeeController {
        public static void main(String[] args) {

            SessionFactory factory = new Configuration().configure().buildSessionFactory();
            Session session = factory.openSession();

            Transaction tx = session.beginTransaction();

            factory.close();
            session.close();
        }

        public static void findEmployeeByName() {

            SessionFactory factory = new Configuration().configure().buildSessionFactory();
            Session session = factory.openSession();

            Transaction tx = session.beginTransaction();

            TypedQuery<model.Employee>query = session.createNamedQuery("findEmployeeByName", model.Employee.class);
            query.setParameter("name","Tom Thele");
            List<model.Employee> employees=query.getResultList();
            System.out.printf("%-10s%-22s%-14s%-14s%-16s%-10s%-10s%s%n",
                    "ID","Name","Salary", "Job","AddressLine","Zipcode","city","startDate");
            for (model.Employee e : employees) {
                System.out.printf("%-11d%-22s%-14d%-14s%-16s%-10s%-10s%s%n",e.getId(),e.getName(),
                        e.getSalary(),e.getJob(),e.getAddressLine(),e.getZipcode(),e.getCity(),e.getStartDate());
            }
            factory.close();
            session.close();
        }

        public static void findEmployeeById() {

            SessionFactory factory = new Configuration().configure().buildSessionFactory();
            Session session = factory.openSession();

            Transaction tx = session.beginTransaction();

            TypedQuery<Object[]> query = session.createNamedQuery("getEmployeeById", Object[].class);
            query.setParameter("id",3);
            List<Object[]> employee= query.getResultList();
            System.out.printf("%-10s%-12s%s%n", "Name","Salary", "Job");
            for (Object[] e : employee) {
                System.out.printf("%-11s%-12s%s%n",e[0],e[1],e[2]);
            }
            factory.close();
            session.close();
        }

        public static void showOfficeCodesAsDepartment() {

            SessionFactory factory = new Configuration().configure().buildSessionFactory();
            Session session = factory.openSession();

            Transaction tx = session.beginTransaction();

            TypedQuery<Object[]> query = session.createNamedQuery("employeeDeptAlias",
                    Object[].class);
            List<Object[]> employees = query.getResultList();
            System.out.printf("%-15s%-17s%s%n", "OfficeCode","Dep Name","Employee Name");
            for (Object[] e : employees) {
                System.out.printf(" %-15s%-17s%s%n",e[1],e[3],e[2]);
            }
            factory.close();
            session.close();
        }
    }

