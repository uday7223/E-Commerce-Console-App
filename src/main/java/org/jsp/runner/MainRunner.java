package org.jsp.runner;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.entity.Product;
import org.jsp.entity.User;

public class MainRunner {
    static Scanner sc = new Scanner(System.in);
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
    static EntityManager em = emf.createEntityManager();
    static EntityTransaction transaction = em.getTransaction();
    static User u = new User();
    static Product p = new Product();

    public static void main(String[] args) {
        System.out.print("Hey, Hello there !! \n \n1. User - Login \n2. Admin \n Choose your operation: ");
        int userOption = 1;
        int Admin = 2;
        int option = sc.nextInt();

        if (Admin == option) {
            System.out.print("\n1. Create User\n2. Display User\n3. Update User\n4. Delete User\n5. View User by userId\n6. View User by userName\n7. Delete User By Email And Password\nChoose your operation: ");
            int userInput = sc.nextInt();

            switch (userInput) {
                case 1:
                    createNewUser();
                    break;
                case 2:
                    displayUser();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    viewUserByUserId();
                    break;
                case 6:
                    viewUserByUserName();
                    break;
                case 7:
                	deleteUserByEmailAndPassword();
                	break;
              
                default:
                    break;
            }
        } else if (userOption == option) {
        	  if (userLogin()) {
                  productOperations();
              } else {
                  System.out.println("Login failed! Incorrect email or password.");
              }
        }
    }

    static void createNewUser() {
        System.out.print("Enter Name: ");
        u.setName(sc.next());
        System.out.print("Enter Email: ");
        u.setEmail(sc.next());
        System.out.print("Enter Phone: ");
        u.setPhone(sc.nextLong());
        System.out.print("Enter Password: ");
        u.setPassword(sc.next());

        transaction.begin();
        em.persist(u);
        transaction.commit();
    }

    static void deleteUser() {
        System.out.print("Enter the User ID to delete: ");
        User u = em.find(User.class, sc.nextInt());

        transaction.begin();
        em.remove(u);
        transaction.commit();
        System.out.println("User Deleted!");
    }

    static void updateUser() {
        System.out.print("Enter the User ID to update: ");
        User u = em.find(User.class, sc.nextInt());
        System.out.print("Enter the new User Name: ");
        u.setName(sc.next());

        transaction.begin();
        em.merge(u);
        transaction.commit();
        System.out.println("User name updated: " + u.getName());
    }

    static void displayUser() {
        System.out.println(em.createQuery("from User u").getResultList());
    }

    static void viewUserByUserId() {
        System.out.print("Enter the User ID to display: ");
        Query query = em.createQuery("select u from User u where u.id = ?1");
        query.setParameter(1, sc.nextInt());
        List<User> list = query.getResultList();
        System.out.println(list);
    }

    static void viewUserByUserName() {
        System.out.print("Enter the User Name to display: ");
        Query query = em.createQuery("select u from User u where u.name = ?1");
        query.setParameter(1, sc.next());
        List<User> list = query.getResultList();
        System.out.println(list);
    }
    
    static boolean deleteUserByEmailAndPassword() {
        System.out.print("Enter Email: ");
        String email = sc.next();
        System.out.print("Enter Password: ");
        String password = sc.next();

        Query query = em.createQuery("select u from User u where u.email = :email and u.password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);

        List<User> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            em.getTransaction().begin();
            for (User user : resultList) {
                em.remove(user);
            }
            em.getTransaction().commit();
            return true;
        }
        return false;
    }


    static boolean userLogin() {
        System.out.print("Enter Email: ");
        String email = sc.next();
        System.out.print("Enter Password: ");
        String password = sc.next();

        Query query = em.createQuery("select u from User u where u.email = :email and u.password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);

        List<User> resultList = query.getResultList();
        System.out.println(resultList);
        return !resultList.isEmpty();
    }

    static void productOperations() {
        System.out.print("\n1. Create Product\n2. Display Product\n3. Update Product Details\n4. Delete Product\nChoose your operation: ");
        int productInput = sc.nextInt();

        switch (productInput) {
            case 1:
                createNewProduct();
                break;
            case 2:
                displayProduct();
                break;
            case 3:
                updateProduct();
                break;
            case 4:
                deleteProduct();
                break;
            default:
                break;
        }
    }

    static void createNewProduct() {
        System.out.print("Enter Product Name: ");
        p.setName(sc.next());
        System.out.print("Enter Price: ");
        p.setPrice(sc.nextInt());

        transaction.begin();
        em.persist(p);
        transaction.commit();
    }

    static void deleteProduct() {
        System.out.print("Enter the Product ID to delete: ");
        Product p = em.find(Product.class, sc.nextInt());

        transaction.begin();
        em.remove(p);
        transaction.commit();
        System.out.println("Product Deleted: " + p.getName());
    }

    static void updateProduct() {
        System.out.print("Enter the Product ID to update: ");
        Product p = em.find(Product.class, sc.nextInt());
        System.out.print("Enter the new Product Name: ");
        p.setName(sc.next());

        transaction.begin();
        em.merge(p);
        transaction.commit();
        System.out.println("Product name updated: " + p.getName());
    }

    static void displayProduct() {
        System.out.println(em.createQuery("from Product p").getResultList());
    }
}
