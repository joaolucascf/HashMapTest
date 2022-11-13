import java.io.IOException;
import java.util.*;

public class HashMapTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        HashMapTest app = new HashMapTest();
        app.App();
    }

    public int App() throws IOException, InterruptedException {
        HashMap<Integer, Product> produtos = new HashMap<Integer, Product>();
        Scanner sc = new Scanner(System.in);
        for(;;){
            showMenu();
            try {
                switch (choiceMenu()) {
                    case 1 -> addProduct(produtos, sc);
                    case 2 -> modifyProduct(produtos, sc);
                    case 3 -> removeProduct(produtos,sc);
                    case 4 -> {
                        listProduct(produtos);
                        System.in.read();
                    }
                    case 5 -> {
                        sc.close();
                        return 0;
                    }
                    default -> throw new IllegalStateException();
                }
            }
            catch (IllegalStateException e){
                System.out.println("OutOfControll");
            }
        }
    }

    private int choiceMenu() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public void showMenu() throws IOException, InterruptedException {
        clearScreen();
        System.out.println("---MENU---");
        System.out.println("1 - Adicionar");
        System.out.println("2 - Modificar");
        System.out.println("3 - Remover");
        System.out.println("4 - Listar");
        System.out.println("5 - Sair");
    }

    public void removeProduct(HashMap<Integer, Product> p, Scanner sc) throws IOException, InterruptedException {
        if(p.isEmpty()) {
            System.out.println("o HashMap está vazio.");
            return;
        }
        listProduct(p);
        System.out.println("Digite o índice do produto que deseja remover: ");
        int index = sc.nextInt();
        sc.nextLine();
        if(!p.containsKey(index)){
            System.out.println("O índice informado é invalido.");
            System.out.println("Pressione ENTER para voltar ao menu");
            System.in.read();
            return;
        }
        p.remove(index);
        System.out.println("Item removido com sucesso");
        System.out.println("Pressione ENTER para voltar ao menu");
        System.in.read();
    }

    public void modifyProduct(HashMap<Integer, Product> p, Scanner sc) throws IOException, InterruptedException {
        if(p.isEmpty()){
            System.out.println("O HashMap está vazio.");
            return;
        }
        listProduct(p);
        System.out.println("Digite o índice do produto que deseja modificar: ");
        int index = sc.nextInt();
        sc.nextLine();
        if(!p.containsKey(index)){
            System.out.println("O índice informado é invalido.");
            System.out.println("Pressione ENTER para voltar ao menu");
            System.in.read();
            return;
        }
        System.out.printf("Digite o novo nome desse produto: ");
        String nameProduct = sc.nextLine();
        System.out.printf("Digite o novo valor desse produto: ");
        double priceProduct = sc.nextDouble();
        sc.nextLine();
        p.put(index, new Product(nameProduct, priceProduct));
        System.out.println("Item modificado com sucesso.");
        System.out.println("Pressione ENTER para voltar ao menu");
        System.in.read();
    }

    public void addProduct(HashMap<Integer, Product> p, Scanner sc) throws IOException, InterruptedException {
        clearScreen();
        System.out.printf("Digite o nome do produto: ");
        String nameNewProduct = sc.nextLine();
        System.out.printf("Digite o preço do produto: ");
        double priceNewProduct = sc.nextDouble();
        sc.nextLine();
        int nextNode = findNextNode(p);
        p.put(nextNode, new Product(nameNewProduct, priceNewProduct));
    }

    public int findNextNode(HashMap<Integer, Product> p){
        if(!p.isEmpty()) {
            return (Collections.max(p.keySet())+1);
        }
        return 1;
    }

    public void listProduct(HashMap<Integer, Product> p) throws IOException, InterruptedException {
        clearScreen();
        System.out.println("---LISTA DE PRODUTOS---");
        for(Map.Entry<Integer, Product> products: p.entrySet()){
            System.out.format("%d: %s - R$%.2f%n", products.getKey(), products.getValue().getName(), products.getValue().getPrice());
        }
    }

    public void clearScreen() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}