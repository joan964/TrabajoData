package prog.cipfpbatoi;

import java.util.StringTokenizer;

public class Data {
	
	private int dia;
	
	private int mes;
	
	private int any;


	private static final String[] DIES_TEXT = new String[] { "diumenge", "dilluns", "dimarts", "dimecres", "dijous", "divendres",
			"dissabte"};

	private static final String[] MESOS_TEXT = new String[] { "gener", "febrer", "març", "abril", "maig", "juny",
			"juliol", "agost", "setembre", "octubre", "novembre", "desembre" };
	/**
	 *  Constructor por defecto
	 *  Inicializa una fecha a dia 1-1-1970
	 */
	public Data() {

		this.dia=1;
		this.mes=1;
		this.any=1970;
	}
	/**
	 *  Inicializa la fecha
	 *  @param dia de la semana
	 *  @param mes del año
	 *  @paramanyo
	 */
	public Data(int dia, int mes, int any) {
		this.dia=dia;
		this.mes=mes;
		this.any=any;
	}

	/**
	 * Inicializa la fecha a partir de otra pasada en formato String dd/mm/yyyy
	 *
	 * Deberemos trocearlas de forma que asignemos el día més y año a cada uno de los atributo
         * Tienes que utilizar las funciones de *String o consultar la documentación oficial y hacerlo OBLIGATORIAMENTE 
         * con la clase StringTokenizer. 
         * Si el formato recibido no es el correcto, creará la fecha por defecto.
	 * @paramfecha
	 */
	public Data(String data) {

		StringTokenizer srt=new StringTokenizer(data,"/");

		String [] numeros=new String[3];
		for (int i = 0; i < 3; i++) {
			numeros[i]=srt.nextToken();
		}
		this.dia= Integer.parseInt(numeros[0]);
		this.mes= Integer.parseInt(numeros[1]);
		this.any= Integer.parseInt(numeros[2]);
		// Tu código aquí
	}

	/**
	 * Modifica la fecha actual a partir de los datos pasados como argumento
	 */
	public void set(int dia, int mes, int anyo) {
		this.dia=dia;
		this.mes=mes;
		this.any=anyo;
	}
	/**
	 * Obtiene una fecha con los mismos atributos que la fecha actual
	 * @return
	 */
	public Data clone() {
		return new Data(this.dia,this.mes,this.any);
	}

	/**
	 * Devuelve el día de la semana que representa por la Fecha actual
	 * @return @dia
	 */
	public int getDia() {
		return dia;
	}

	/**
	 * Devuelve el mes que representa la Fecha actual
	 * @return @mes
	 */
	public int getMes(){
		return mes;
	}

	/**
	 * Devuelve el año que representa la Fecha actual
	 * @return @mes
	 */
	public int getAny(){
		return any;
	}

	/**
	 * Muestra por pantalla la fecha en formato español dd-mm-yyyy
	 */
	public void mostrarEnFormatES()  {
		System.out.printf("%02d-%02d-%02d",dia,mes,any);
		System.out.println();
	}

	/**
	 * Muestra por pantalla la fecha en formato inglés yyyy-mm-dd
	 */
	public void mostrarEnFormatGB() {
		System.out.printf("%02d-%02d-%02d",any,mes,dia);
		System.out.println();
	}

	/**
	 * Muestra por pantalla la fecha en formato texto dd-mmmm-yyyy
	 * Ej. 1 enero de 1970
	 */
	public void mostrarEnFormatText() {
		int posicionArray = mes - 1;

		System.out.printf("%02d-" + MESOS_TEXT[posicionArray] + "-%02d", dia, any);
		System.out.println();
	}


	/**
	 * Retorna un booleano indicando si la fecha del objeto es igual a la fecha pasada como
	 * argumento
	 *
	 * @return boolean
	 */
	public boolean isIgual(Data otraFecha) {
		System.out.println();
		if (dia == otraFecha.dia && mes == otraFecha.mes && any==otraFecha.any){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Retorna un String que representa el dia de la setmana en format text (dilluns, dimarts, dimecres, dijous, divendres, dissabte, diumenge).
         * L'algorisme de resolució d'aquest mètode es troba al enunciat.
	 * @return String
	 */
	public String getDiaSetmana() {

		int diesTranscorreguts=getDiesTranscorregutsOrigen();
		int modDia=diesTranscorreguts%7;

		return DIES_TEXT[modDia];
	}

	/**
	 * Retorna un booleà indicant si la data representada per l'objecte actual és festiva. Es considerarà festiu si el dia de la setmana és dissabte o diumenge
	 * @return boolean
	 */
	public boolean isFestiu() {

		if (getDiaSetmana().equals("dissabte") || getDiaSetmana().equals("diumenge")){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Retorna el número de la setmana dins de l'any actual. 
         * Es considera una setmana l'interval de dates entre una data que siga dilluns i la següent data en ordre cronològic que siga diumenge. 
         * També es comptabilitza com a setmana tant la primera setmana de l'any com l'última (inclusivament en aquells anys en què la primera i/o 
         * última setmana no conté set dies en total).
	 *
	 * @return int dia semana
	 */
	public int getNumeroSetmana() {

		int contadorSemana=getDiesTranscorregutsEnAny()/7;
		float contadorSemana2=(float) getDiesTranscorregutsEnAny()/7;

		if (contadorSemana2>(float) contadorSemana){
			contadorSemana++;
		}
		return contadorSemana;
	}

	/**
	 * Retorna un nou objecte de tipus data que representa la data resultant d'afegir el nombre de dies passats com a argument a la data que representa l'objecte actual. 
         * Haurem de tindre en compte els dies que té el mes actual i si l'any és de traspàs (bisiesto) 
         * amb la finalitat de construir el nou objecte amb la data correcta. 
         * El màxim nombre de dies que podrem afegir serà 30 i no podrem afegir un nombre negatiu de dies. 
	 *
	 * @return boolean
	 */
	public Data afegir(int dies) {

		Data novaData = clone();

		for (int i = 0; i < dies; i++) {
			novaData.dia++;

			if (novaData.dia > getDiesMes(novaData.mes, novaData.any)) {
				novaData.dia = 1;
				novaData.mes++;

				if (novaData.mes > 12) {
					novaData.mes = 1;
					novaData.any++;
					novaData.dia--;
				}
			}
		}
		System.out.println(novaData.dia+" "+novaData.mes+" "+novaData.any);

		return novaData;
	}


	/**
	 * Retorna un nou objecte de tipus data que representa la data resultant de restar el nombre de dies passats com a argument a la data que representa l'objecte actual. 
         * Haurem de tindre en compte els dies que té el mes actual i si l'any és de traspàs (bisiesto) amb la finalitat de construir el nou objecte amb la data correcta.  
         * El màxim nombre de dies que podrem restar serà 30 i no podrem restar un nombre negatiu de dies. 
	 *
	 * @return boolean
	 */
	public Data restar(int dies) {
		Data novaData = clone();

		for (int i = 0; i < dies; i++) {
			novaData.dia--;

			if (novaData.dia <= 0) {
				novaData.mes--;

				if (novaData.mes <= 0) {
					novaData.dia++;
					novaData.mes = 12;
					novaData.any--;
				}

				novaData.dia = getDiesMes(novaData.mes, novaData.any);
			}
		}
		System.out.println(novaData.dia+" "+novaData.mes+" "+novaData.any);

		return novaData;
	}


	/**
         * Retorna un booleà indicant si la data representada per l'objecte actual és correcta. 
         * No oblides comprovar que el dia es trobe dins del rang dels dies que té el mes tenint en compte si l'any és de traspàs(bisiesto) o no.
         * @return 
         */
	public boolean isCorrecta() {

		if (mes < 1 || mes > 12) {
			return false;
		}
		if (dia < 1 || dia > getDiesMes(mes, any)) {
			return false;
		}
		return true;
	}


	/**
	 * Retorna el mes del año en formato text (enero, febrero, marzo,...)
	 * @return char
	 */
	private String getMesEnFormatText() {
		return MESOS_TEXT[mes];
	}

	/**
	 * Devuelve el número de dias transcurridos desde el 1-1-1
	 *
	 * @return int
	 */
	private int getDiesTranscorregutsOrigen() {

		int diesTranscorreguts = 0;

		for (int anyoActual = 1; anyoActual < any; anyoActual++) {
			diesTranscorreguts += getDiesAny(anyoActual);
		}

		diesTranscorreguts += getDiesTranscorregutsEnAny();

		return diesTranscorreguts;
	}

	/**
	 * Devuelve el número de dias transcurridos en el anyo que representa el objeto
	 *
	 * @return int
	 */
	private int getDiesTranscorregutsEnAny() {

		int diesTranscorreguts = 0;

		for (int i = 1; i < mes; i++) {
			diesTranscorreguts += getDiesMes(i, any);
		}


		diesTranscorreguts += dia;
		return diesTranscorreguts;
	}

	/**
	 * Indica si el año pasado como argumento es bisiesto. Un año es bisiesto si es divisible por 4
	 * pero no es divisible entre 100 o es divisible entre 4 entre 100 y entre 400
	 *
	 * @return boolean
	 */
	public static boolean isBisiesto(int anyo){

		boolean bisiesto = false;

		if (anyo%400==0){
			bisiesto=true;
		} else if (anyo%4==0 && anyo%100 !=0) {
			bisiesto=true;
		}
		return bisiesto;
	}

	/**
	 *  Calcula el número de días que tiene el @mes en el @año pasado como argumento
	 *  Deberás hacer uso del métodos isBisiesto
	 *
	 *  @return int total dias mes en curso
	 */
	public static int getDiesMes (int mes, int anyo) {

		int diasMés=0;
		boolean esBisisto=isBisiesto(anyo);

		if (mes==2){
			if (esBisisto){
				System.out.println("es bisiesto");
				diasMés=29;
			}else {
				System.out.println("NO es bisiesto");
				diasMés=28;
			}
		} else if (mes%2==0) {
			diasMés=30;
		}else {
			diasMés=31;
		}

		// mes de la arrayy de los meses, anyo para saber si es bisiesto el anyo, si es bisioto febrero tiene 29 si no 28
		return diasMés;
	}

	/**
	 * Calcula el número total de dias que tiene el año pasado como argumento
	 *
	 * @return int total dias anyo en curso
	 */
	public static int getDiesAny (int anyo){
		int años=0;
		if (isBisiesto(anyo)){
			años=366;
		}else {
			años=365;
		}
		return años;
	}
}
