package domain;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Casilla extends JButton{
	
        private boolean hayBarco;
        private boolean destapado;

        public Casilla() {
        	super();
            this.hayBarco = false;
            this.destapado = false;
        }

        public boolean isHayBarco() {
            return hayBarco;
        }

        public void setHayBarco(boolean hayBarco) {
            this.hayBarco = hayBarco;
        }

		public boolean isDestapado() {
			return destapado;
		}

		public void setDestapado(boolean destapado) {
			this.destapado = destapado;
		}
        
}