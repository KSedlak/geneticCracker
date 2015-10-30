package geneticCracker.view.table;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class NgramsTableRow {


		private SimpleStringProperty value;

		private SimpleIntegerProperty quantity;

		public NgramsTableRow(SimpleStringProperty value, SimpleIntegerProperty quantity) {
			super();
			this.value = value;
			this.quantity = quantity;
		}

		public SimpleStringProperty getValue() {
			return value;
		}

		public void setValue(SimpleStringProperty value) {
			this.value = value;
		}

		public SimpleIntegerProperty getQuantity() {
			return quantity;
		}

		public void setQuantity(SimpleIntegerProperty quantity) {
			this.quantity = quantity;
		}
		
}
