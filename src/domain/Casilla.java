package domain;

/**
 * Clase Casilla
 * @author Izaro, Genesis, Ainhoa
 */

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Casilla extends JButton{
	
        private boolean hayBarco;
        private boolean destapado;
        
       
        /**
         * Constructor de Casilla
         */
        public Casilla() {
        	super();
            this.hayBarco = false;
            this.destapado = false;
        }
        
        /**
         * Metodo para obtener si la celda tiene un barco o no
         * @return booleano que indica si la celda tiene barco
         */

        public boolean isHayBarco() {
            return hayBarco;
        }
        /**
         * Metodo para actualizar si hay barco
         * @param hayBarco booleano que indica si hay un barco en la celda
         */
        public void setHayBarco(boolean hayBarco) {
            this.hayBarco = hayBarco;
        }
        
        /**
         * Metodo para saber si la celda ha sido destapada
         * @return booleano que indica si esta destapado
         */
		public boolean isDestapado() {
			return destapado;
		}
		
		/**
		 * Metodo para actulizar el estado de la celda
		 * @param destapado booleano que indica si la celda esta destapada
		 */
		public void setDestapado(boolean destapado) {
			this.destapado = destapado;
		}
        
}