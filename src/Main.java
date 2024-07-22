import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Magasin magasin = new Magasin();

    private static Map<Integer, Produit> productMap = new HashMap<>();
    private static final String PRODUCT_FILE = "products.txt";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            displayMenu();
            choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                    ajouterProduit(scanner);
                    break;
                case 2:
                    supprimerProduit(scanner);
                    break;
                case 3:
                    ModifierProduit(scanner);
                    break;
                case 4:
                    ModifierQuabtiteProduit(scanner);
                    break;
                case 5:
                    RechercherProduitparNom(scanner);
                    break;
                case 6:
                    ListerProduitparLettre(scanner);
                    break;
                case 7:
                    AfficherNombreProduitenStock();
                    break;
                case 8:
                    AfficherNombredeProduitsparType();
                    break;
                case 9:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");

            }

            System.out.println();
        } while (choix != 7);

    }
    private static void displayMenu() {
        System.out.println("Gestion des produits :");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Supprimer un produit");
        System.out.println("3. Modifier un produit");
        System.out.println("4. Modifier la quantité d'un produit");
        System.out.println("5. Rechercher un produit par nom");
        System.out.println("6. Lister les produits par lettre");
        System.out.println("7. Afficher le nombre de produits en stock");
        System.out.println("8. Afficher le nombre de produits par type");
        System.out.println("9. Quitter");
        System.out.print("Entrez votre choix : ");
    }
    private static void ajouterProduit(Scanner scanner) {
        System.out.print("Entrez le type de produit (Electronique/Alimentaire/Vestimentaire) : ");
        String type = scanner.nextLine();

        switch (type.toLowerCase()) {
            case "electronique":
                addElectronicProduct(scanner);
                break;
            case "alimentaire":
                addFoodProduct(scanner);
                break;
            case "vestimentaire":
                addClothingProduct(scanner);
                break;
            default:
                System.out.println("Type de produit invalide. Veuillez réessayer.");
                return;
        }

    }
    private static void addElectronicProduct(Scanner scanner) {
        System.out.print("Entrez l'identifiant du produit : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (productMap.containsKey(id)) {
            System.out.println("Ce produit existe déjà. Veuillez entrer un nouvel identifiant.");
            return;
        }

        System.out.print("Entrez le nom du produit : ");
        String name = scanner.nextLine();
        System.out.print("Entrez la quantité en stock : ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Entrez la garantie (en années) : ");
        int garantie = scanner.nextInt();
        System.out.print("Entrez le prix du produit : ");
        double prix = scanner.nextDouble();
        scanner.nextLine();

        Electronique product = new Electronique(id, name, quantity, garantie, prix);
        magasin.addProduct(product);
        writeProductToFile(product);
        System.out.println("Produit électronique ajouté avec succès.");
    }
    private static void addFoodProduct(Scanner scanner) {
        System.out.print("Entrez l'identifiant du produit : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (productMap.containsKey(id)) {
            System.out.println("Ce produit existe déjà. Veuillez entrer un nouvel identifiant.");
            return;
        }

        System.out.print("Entrez le nom du produit : ");
        String name = scanner.nextLine();
        System.out.print("Entrez la quantité en stock : ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Entrez la date d'Expiration : ");
        String Dateexpiration= scanner.nextLine();
        System.out.print("Entrez le prix du produit : ");
        double prix = scanner.nextDouble();
        scanner.nextLine();

        Alimentaire product = new Alimentaire(id, name, quantity, Dateexpiration, prix);
        magasin.addProduct(product);
        writeProductToFile(product);
        System.out.println("Produit alimentaire ajouté avec succès.");

    }
    private static void addClothingProduct(Scanner scanner) {
        System.out.print("Entrez l'identifiant du produit : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (productMap.containsKey(id)) {
            System.out.println("Ce produit existe déjà. Veuillez entrer un nouvel identifiant.");
            return;
        }

        System.out.print("Entrez le nom du produit : ");
        String name = scanner.nextLine();
        System.out.print("Entrez la quantité en stock : ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Entrez la taille de l'habit: ");
        String taille= scanner.nextLine();
        System.out.print("Entrez le prix du produit : ");
        double prix = scanner.nextDouble();
        scanner.nextLine();

        Vestimentaire product = new Vestimentaire(id, name, quantity, taille, prix);
        magasin.addProduct(product);
        writeProductToFile(product);
        System.out.println("Produit alimentaire ajouté avec succès.");

    }

    private static void supprimerProduit(Scanner scanner) {
        System.out.print("Entrez l'identifiant du produit à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (magasin.removeProduct(id)) {
            System.out.println("Produit supprimé avec succès.");
        } else {
            System.out.println("Produit non trouvé.");
        }
    }

    private static void ModifierProduit(Scanner scanner) {
        System.out.print("Entrez l'identifiant du produit à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Produit product = magasin.getProductById(id);
        if (product != null) {
            System.out.print("Entrez le nouveau nom du produit : ");
            String newName = scanner.nextLine();
            System.out.print("Entrez la nouvelle quantité en stock : ");
            int newQuantity = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Entrez le nouveau prix du produit: ");
            double newPrix = scanner.nextDouble();
            scanner.nextLine();

            product.setName(newName);
            product.setQuantity(newQuantity);
            product.setPrix(newPrix);
            System.out.println("Produit mis à jour avec succès.");
        } else {
            System.out.println("Produit non trouvé.");
        }
    }
    private static void ModifierQuabtiteProduit(Scanner scanner){
        System.out.print("Entrez l'identifiant du produit dont vous voulez modifier la quantité : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Produit product = magasin.getProductById(id);
        if (product != null){
            System.out.print("Entrez la nouvelle quantité en stock : ");
            int newQuantity = scanner.nextInt();
            scanner.nextLine();
            product.setQuantity(newQuantity);
            System.out.println("Quantité du produit mise à jour avec succès.");

        }else{
            System.out.println("Produit non trouvé.");
        }
    }

    private static void RechercherProduitparNom(Scanner scanner) {
        System.out.print("Entrez le nom du produit à rechercher : ");
        String name = scanner.nextLine();

        ArrayList<Produit> foundProducts = magasin.searchProductByName(name);
        if (foundProducts.isEmpty()) {
            System.out.println("Aucun produit trouvé avec ce nom.");
        } else {
            for (Produit product : foundProducts) {
                System.out.println(product);
            }
        }
    }

    private static void ListerProduitparLettre(Scanner scanner) {
        System.out.print("Entrez la lettre pour lister les produits : ");
        char letter = scanner.nextLine().charAt(0);

        ArrayList<Produit> foundProducts = magasin.listProductsByLetter(letter);
        if (foundProducts.isEmpty()) {
            System.out.println("Aucun produit trouvé commençant par cette lettre.");
        } else {
            for (Produit product : foundProducts) {
                System.out.println(product);
            }
        }
    }

    private static void AfficherNombreProduitenStock() {
        System.out.println("Nombre de produits en stock : " + magasin.getProductCount());
    }
    private static void AfficherNombredeProduitsparType() {
        System.out.println("Nombre de produits par type :");
        System.out.println("Alimentaire : " + magasin.getProductCountByType("Alimentaire"));
        System.out.println("Électronique : " + magasin.getProductCountByType("Électronique"));
        System.out.println("Vestimentaire : " + magasin.getProductCountByType("Vestimentaire"));
    }
}
private static void writeProductToFile(Produit produit) {
    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter(PRODUCT_FILE, true));
        writer.write(produit.toString());
        writer.newLine();
        writer.close();
    } catch (IOException e) {
        System.out.println("Erreur lors de l'écriture du produit dans le fichier : " + e.getMessage());
    }
}

