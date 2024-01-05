package domain;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class RendererTabla extends DefaultTableCellRenderer{
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col)
	{
		Component celda = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
		
		if(row == 0)
		{
			celda.setBackground(Color.YELLOW);
		}
		else if(row == 1)
		{
			celda.setBackground(Color.GRAY);
		}
		else if(row == 2)
		{
			celda.setBackground(Color.BLUE);
		}
		else
		{
			celda.setBackground(table.getBackground());
		}
		
		return celda;
	}

}
