import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class PasswordGenerator {
    private LinkedList<Character> linkedList;
    private Stack<Character> stack;
    private Graph graph;
    
    public PasswordGenerator() {
        linkedList = new LinkedList<>();
        stack = new Stack<>();
        graph = new Graph(52);
        
        // Inicializar la lista enlazada con caracteres posibles
        for (char c = 'a'; c <= 'z'; c++) {
            linkedList.add(c);
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            linkedList.add(c);
        }
        for (char c = '0'; c <= '9'; c++) {
            linkedList.add(c);
        }
        
        // Inicializar la pila con caracteres especiales
        stack.push('!');
        stack.push('@');
        stack.push('#');
        stack.push('$');
        stack.push('%');
        stack.push('&');
        
        // Construir el grafo con algunas conexiones entre caracteres
        graph.addEdge('a', 'A');
        graph.addEdge('b', 'B');
        graph.addEdge('c', 'C');
        graph.addEdge('d', 'D');
        graph.addEdge('e', 'E');
        graph.addEdge('f', 'F');
        graph.addEdge('g', 'G');
    }
    
    public String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < length; i++) {
            // Elegir aleatoriamente una estructura de datos
            int structure = random.nextInt(3);
            
            // Generar un carácter aleatorio dependiendo de la estructura elegida
            char c;
            if (structure == 0) {
                c = linkedList.remove(random.nextInt(linkedList.size()));
            } else if (structure == 1) {
                c = stack.pop();
            } else {
                c = graph.getRandomNeighbor(random.nextInt(graph.getNumVertices()));
            }
            
            password.append(c);
        }
        
        return password.toString();
    }
    
    public static void main(String[] args) {
        PasswordGenerator generator = new PasswordGenerator();
        String password = generator.generatePassword(10);
        System.out.println("Contraseña generada: " + password);
    }
}

class Graph {
    private LinkedList<Character>[] adjList;
    
    public Graph(int numVertices) {
        adjList = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }
    
    public void addEdge(char src, char dest) {
        int srcIndex = src - 'a';
        int destIndex = dest - 'A' + 26; // Agregar un desplazamiento para las letras mayúsculas
        adjList[srcIndex].add(dest);
        adjList[destIndex].add(src);
    }
    
    public char getRandomNeighbor(int vertex) {
        LinkedList<Character> neighbors = adjList[vertex];
        Random random = new Random();
        
        if (neighbors.isEmpty()) {
            // Si no hay vecinos, seleccionar un carácter aleatorio del conjunto completo
            int index = random.nextInt(getNumVertices());
            return (char) ('a' + index);
        } else {
            // Seleccionar un vecino aleatorio de la lista de vecinos
            int index = random.nextInt(neighbors.size());
            return neighbors.get(index);
        }
    }

    
    public int getNumVertices() {
        return adjList.length;
    }
}
