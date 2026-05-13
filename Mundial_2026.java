
package mundial_2026;
import java.util.Scanner;

class Seleccion {
    String nombre;
    int puntos;
    int diferenciaGol;
    int partidosJugados;

    public Seleccion(String nombre) {
        this.nombre = nombre;
    }

    public void registrar(int p, int g) {
        this.puntos += p;
        this.diferenciaGol += g;
        this.partidosJugados ++;
    }
}

class Grupo {
    String nombreGrupo;
    Seleccion[] equipos;

    public Grupo(String nombre, String[] nombresEquipos) {
        this.nombreGrupo = nombre;
        this.equipos = new Seleccion[4];
        for (int i = 0; i < 4; i++) {
            equipos[i] = new Seleccion(nombresEquipos[i]);
        }
    }

public void jugarPartido(int posicionLocal, int golesLocal, int posicionVisita, int golesVisita) {
    
    int puntosLocal = 0;
    int puntosVisita = 0;
    
    if (golesLocal > golesVisita) { 
        puntosLocal = 3;
        puntosVisita = 0;
    } 
    else if (golesLocal == golesVisita) { 
        puntosLocal = 1;
        puntosVisita = 1;
    } 
    else { 
        puntosLocal = 0;
        puntosVisita = 3;
    }

    int diferenciaLocal = golesLocal - golesVisita;
    int diferenciaVisita = golesVisita - golesLocal;

    equipos[posicionLocal].registrar(puntosLocal, diferenciaLocal);
    equipos[posicionVisita].registrar(puntosVisita, diferenciaVisita);
}

    public void ordenarTabla() {
        for (int i = 0; i < equipos.length; i++) {
            for (int j = 0; j < equipos.length - 1; j++) {
                boolean cambiar = false;
                if (equipos[j].puntos < equipos[j + 1].puntos) {
                    cambiar = true;
                } else if (equipos[j].puntos == equipos[j + 1].puntos) {
                    if (equipos[j].diferenciaGol < equipos[j + 1].diferenciaGol) cambiar = true;
                }
                if (cambiar) {
                    Seleccion temp = equipos[j];
                    equipos[j] = equipos[j + 1];
                    equipos[j + 1] = temp;
                }
            }
        }
    }

    public void mostrarTabla(int fecha) {
        System.out.println("\n--- TABLA " + nombreGrupo + " - FECHA " + fecha + " ---");
        System.out.printf("%-15s %-5s %-5s %-5s%n", "Equipo", "PTS", "DG", "PJ");
        for (Seleccion s : equipos) {
            System.out.printf("%-15s %-5d %-5d %-5d%n", s.nombre, s.puntos, s.diferenciaGol, s.partidosJugados);
        }
    }
}

public class Mundial_2026{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Grupo[] grupos = new Grupo[12];
        grupos[0] = new Grupo("GRUPO A", new String[]{"Mexico", "Sudafrica", "Corea del Sur", "Chequia"});
        grupos[1] = new Grupo("GRUPO B", new String[]{"Canada", "Boznia y Herzegovina", "Catar", "Suiza"});
        grupos[2] = new Grupo("GRUPO C", new String[]{"Brasil", "Marruecos", "Haiti", "Escocia"});
        grupos[3] = new Grupo("GRUPO D", new String[]{"Estados Unidos", "Paraguay", "Australia", "Turquia"});
        grupos[4] = new Grupo("GRUPO E", new String[]{"Alemania", "Curazao", "Costa de Marfil", "Ecuador"});
        grupos[5] = new Grupo("GRUPO F", new String[]{"Paises Bajos", "Japon", "Suecia", "Tunez"});
        grupos[6] = new Grupo("GRUPO G", new String[]{"Belgica", "Egipto", "Iran", "Nueva Zelanda"});
        grupos[7] = new Grupo("GRUPO H", new String[]{"España", "Cabo Verde", "Arabia Saudita", "Uruguay"});
        grupos[8] = new Grupo("GRUPO I", new String[]{"Francia", "Senegal", "Irak", "Noruega"});
        grupos[9] = new Grupo("GRUPO J", new String[]{"Argentina", "Argelia", "Austria", "Jordania"});
        grupos[10] = new Grupo("GRUPO K", new String[]{"Portugal", "RD Congo", "Uzbekistan", "Colombia"});
        grupos[11] = new Grupo("GRUPO L", new String[]{"Inglaterra", "Croacia", "Ghana", "Panama"});
        
        
        System.out.println("SIMULADOR MUNDIAL 2026");
        System.out.print("Seleccione un grupo (A-L): ");
        String sel = sc.next().toUpperCase();
        char letraIngresada = sel.charAt(0);
        int indice = letraIngresada - 'A';

        if (indice >= 0 && indice < 12 && grupos[indice] != null) {
            Grupo g = grupos[indice];

            // --- FECHA 1 ---
            System.out.println("\n--- FECHA 1 ---");
            g.jugarPartido(0, 2, 1, 1); // Equipo 0 vs 1
            g.jugarPartido(2, 0, 3, 1); // Equipo 2 vs 3
            g.ordenarTabla();
            g.mostrarTabla(1);

            // --- FECHA 2 ---
            System.out.println("\n--- FECHA 2 ---");
            g.jugarPartido(0, 1, 2, 0); // Equipo 0 vs 2
            g.jugarPartido(1, 3, 3, 0); // Equipo 1 vs 3
            g.ordenarTabla();
            g.mostrarTabla(2);

            // --- FECHA 3 ---
            System.out.println("\n--- FECHA 3 ---");
            g.jugarPartido(0, 1, 3, 1); // Equipo 0 vs 3
            g.jugarPartido(1, 2, 2, 2); // Equipo 1 vs 2
            g.ordenarTabla();
            g.mostrarTabla(3);

        } else {
            System.out.println("Grupo no existente");
        }
    }
}

    

