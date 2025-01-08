import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // GENERAMOS LOS TABLEROS E INICIAMOS EL JUEGO
        Scanner scanner = new Scanner(System.in);
        String[][] tablerousuario=generarTablero();
        String[][] tablerocpu=generarTablero();
        int disparos = 0;
        portada();
        //-----------------------------------------------------
        //PEDIMOS NUMERO DE LANCHAS RIVALES E INSERTAMOS LOS BARCOS Y MOSTRAMOS TABLERO

        int nlanchas = pedirnumerolanchas(scanner);
        int vidascpu = nlanchas;
        insertarbarcos(tablerocpu,nlanchas);
        mostrarTablero(tablerousuario,vidascpu,disparos);
        //-----------------------------------------------------
        //EMPIEZA LA PARTIDA CON EL PRIMER DISPAROS Y SE COMPRUEBA HASTA QUE GANA O PIERDE
        //PARA MODIFICAR NUMERO DE DISPAROS MAXIMO MODIFICAR CONDICION WHILE.
        while(vidascpu!=0 && disparos!=10) {
            String[] coordenadasString = pedirCoordenadasDisparo(scanner, tablerousuario);
            int[] coordenadas = convertirCoordenadas(coordenadasString);
            if(realizarDisparo(tablerousuario, tablerocpu, coordenadas)){
                vidascpu--;
                disparos++;
            }else disparos++;

            mostrarTablero(tablerousuario,vidascpu,disparos);
        }
     comprobarpartida(disparos,vidascpu);
    }
    //genera portada en consola
    public static void portada(){
                System.out.println("==================================");
                System.out.println("==================================");
                System.out.println("|                                |");
                System.out.println("|         Hundir la Flota        |");
                System.out.println("|                                |");
                System.out.println("==================================");
                System.out.println("==================================");
                System.out.println();
                System.out.println("   Presiona Enter para comenzar ");

                Scanner scanner = new Scanner(System.in); scanner.nextLine();
                System.out.println("¡Bienvenido al juego de Hundir la Flota!");

    }
    //funcion para generar tableros
    public static String[][] generarTablero () {

        String[][] tablero = new String[11][11];
        for(int i = 0; i < tablero[0].length; i++) {
            String[] numeros = {"   "," 0 "," 1 "," 2 "," 3 "," 4 ", " 5 ", " 6 ", " 7 ", " 8 ", " 9 "};
            tablero[0][i] = numeros[i];
        }

        for(int j = 1; j < tablero.length; j++) {
            String[] letras = {"   "," A "," B "," C "," D "," E ", " F ", " G ", " H ", " I ", " J "};
            tablero[j][0] = letras[j];
            for(int x =1; x < tablero.length; x++){
                tablero[j][x]=" - ";
            }
        }



        return tablero;
    }
    //funcion para mostrar tableros
    public static void mostrarTablero(String[] [] tablero,int vidascpu, int disparos){
        System.out.println(" ");
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        System.out.println(" ");
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("|||  VIDAS CPU  ||||||||||||||  DISPAROS |||");
        System.out.println("--------------------------------------------");
        System.out.println("|||      "+vidascpu+"      ||||||||||||||     "+disparos+"     |||");
        System.out.println("--------------------------------------------");
        System.out.println(" ");





    }
    //funcion que inserta los barcos en el tablero
    public static void insertarbarcos(String[][] tablero,int lanchas){
        boolean libre = false;
        for(int i=0;i<lanchas;i++) {
            do {
                int randomX = (int) (1 + Math.random() * (10 - 1 + 1));
                int randomY = (int) (1 + Math.random() * (10 - 1 + 1));
                if (tablero[randomX][randomY] == " - ") {
                    libre=true;
                    tablero[randomX][randomY] = " L ";
                }
            }while (!libre);

        }
    }
    //funcion que pide cuantas lanchas rivales habra en la partida
    public static int pedirnumerolanchas(Scanner scanner){
        System.out.println("--------------------------------------------------------");
        System.out.println(" ");
        System.out.println("¿Cuantas lanchas rivales quieres?");
        System.out.println(" ");
        String numeroLanchas = scanner.nextLine();
        int nLanchas =Integer.parseInt(numeroLanchas);
        while(nLanchas>10 || nLanchas<1){
            System.out.println("Numero erroneo, tiene que estar comprendido entre el 10 y el 1");
            nLanchas =Integer.parseInt(scanner.nextLine());
        }
        return nLanchas;
    }
    //funcion que pide las coordenadas del disparo
    public static String[] pedirCoordenadasDisparo(Scanner scanner,String[][] tablero){
       String letra = "";
       String numero = "";
       boolean letracorrecta = false;
       boolean numerocorrecto = false;
       do {


           do {
               System.out.println("Introduce la letra de la fila donde quieres disparar (A-J) ");
               System.out.println(" ");
               letra = scanner.nextLine().toUpperCase();
               if (letra.isEmpty()) {
                   System.out.println("No has introducido una letra");
                   System.out.println(" ");
               } else if (letra.equals("A") || letra.equals("B") || letra.equals("C") || letra.equals("D") ||
                       letra.equals("E") || letra.equals("F") || letra.equals("G") || letra.equals("H") ||
                       letra.equals("I") || letra.equals("J")) {
                   System.out.println("letra correcta");
                   System.out.println(" ");
                   letracorrecta = true;
               } else {
                   System.out.println("No has introducido un valor correcto");
               }
           } while (!letracorrecta);
           do {
               System.out.println("Introduce el numero de la columna donde quieres disparar (0-9)");
               System.out.println(" ");
               numero = scanner.nextLine();

               if (numero.isEmpty()) {
                   System.out.println("No has introducido un numero");
               } else if (numero.equals("0") || numero.equals("1") || numero.equals("2") ||numero.equals("3") ||
                       numero.equals("4") || numero.equals("5") || numero.equals("6") ||
                       numero.equals("7") || numero.equals("8") || numero.equals("9")) {
                   System.out.println("numero correcto");
                   System.out.println(" ");
                   System.out.println(" ");
                   numerocorrecto = true;
               } else {
                   System.out.println("No has introducido un valor correcto");
               }

           } while (!numerocorrecto);

           System.out.println("   Presiona Enter para continuar ");
           scanner.nextLine();

       }while (HaSidoDisparado(letra,numero,tablero));

       String[] resultados ={letra,numero};

       return resultados;
    }
    //funcion que comprueba si esas coordenadas ya han sido disparadas
    public static boolean HaSidoDisparado(String letra,String numero,String[][] tablero){
       String []coordenadasString={letra,numero};
       int[] coordenadasInt=convertirCoordenadas(coordenadasString);
        if(tablero[coordenadasInt[0]][coordenadasInt[1]]==" - "){
            return false;
        }else{
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("Estas coordenadas ya han sido disparadas");
            System.out.println(" ");
            System.out.println(" ");

            return true;
        }
    }
    //funcion para convertir las coordenadas en la posicion de la matriz
    public static int[] convertirCoordenadas(String[] coordenadas){
        int[] resultado = new int[2];
        switch (coordenadas[0]){
            case "A":
                resultado[0]=1;
                break;
            case "B":
                resultado[0]=2;
                break;
            case "C":
                resultado[0]=3;
                break;
            case "D":
                resultado[0]=4;
                break;
            case "E":
                resultado[0]=5;
                break;
            case "F":
                resultado[0]=6;
                break;
            case "G":
                resultado[0]=7;
                break;
            case "H":
                resultado[0]=8;
                break;
            case "I":
                resultado[0]=9;
                break;
            case "J":
                resultado[0]=10;
                break;
            default:
                System.out.println("Error Coordenada fila en convertirCoordenadas");
        }
        int columna =Integer.parseInt(coordenadas[1]);
        columna+=1;
        resultado[1]=columna;
        return resultado;
    }
    //funcion que efectua el disparo
    public static boolean realizarDisparo(String[][] tablerousuario,String[][]tablerocpu,int[]coordenadas){
        int fila=coordenadas[0];
        int columna=coordenadas[1];
        if(tablerocpu[fila][columna]==" L "){
            System.out.println("----------------------------------------------");
            System.out.println("----------------------------------------------");
            System.out.println("----------------------------------------------");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("                  TOCADO            ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("----------------------------------------------");
            System.out.println("----------------------------------------------");
            System.out.println("----------------------------------------------");
            System.out.println();


            tablerousuario[fila][columna]=" X ";
            System.out.println("    Presiona Enter para continuar ");
            Scanner scanner = new Scanner(System.in); scanner.nextLine();
            return true;
        }else{
            System.out.println("----------------------------------------------");
            System.out.println("----------------------------------------------");
            System.out.println("----------------------------------------------");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("                   AGUA            ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("----------------------------------------------");
            System.out.println("----------------------------------------------");
            System.out.println("----------------------------------------------");
            System.out.println();

            tablerousuario[fila][columna]=" A ";
            System.out.println("   Presiona Enter para continuar ");
            Scanner scanner = new Scanner(System.in); scanner.nextLine();
            return false;
        }



    }
    // funcion resumen de la partida
    public static void comprobarpartida(int disparos, int vidascpu){
        if(vidascpu==0){
            System.out.println("¡enhorabuena has ganado! con "+disparos+" disparos");
        }else{
            System.out.println("¡Has perdido te has quedado sin disparos!");
            System.out.println("Vidas restantes CPU: "+vidascpu);
        }
    }
}