package com.gigaspaces.webuitf.datagrid.transactionspanel;

import com.gigaspaces.webuitf.datagrid.DataGridSubPanel;
import com.gigaspaces.webuitf.util.AjaxUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionsPanel extends DataGridSubPanel implements SpaceTransactionsConstants {

	private Logger _logger = Logger.getLogger( this.getClass().getName() );

	private WebElement _gridElement;

	public TransactionsPanel(AjaxUtils helper) {
		super(helper);

		_gridElement = helper.waitForElement( TimeUnit.SECONDS, 2, By.id( TRANSACTIONS_GRID ) );
	}

	public TransactionRow[] getTransactions(){

		List<TransactionRow> list = new ArrayList<TransactionRow>();

		List<WebElement> idColumnElements = _gridElement.findElements( By.className( COL_TRANSACTION_ID_CLASS ) );
		List<WebElement> statusColumnElements = _gridElement.findElements( By.className( COL_TRANSACTION_STATUS_CLASS ) );
		List<WebElement> typeColumnElements = _gridElement.findElements( By.className( COL_TRANSACTION_TYPE_CLASS ) );
		List<WebElement> numOfLockedObjectsColumnElements = _gridElement.findElements( By.className( COL_TRANSACTION_NUM_OF_LOCKED_OBJECTS_CLASS ) );

		int rowsNum = idColumnElements.size();
		for( int rowIndex = 0; rowIndex < rowsNum; rowIndex++ ){
			String id = getTagValue( idColumnElements, rowIndex );
			String status = getTagValue( statusColumnElements, rowIndex );
			String type = getTagValue( typeColumnElements, rowIndex );
			String numOfLockedObjects = getTagValue( numOfLockedObjectsColumnElements, rowIndex );

			list.add( new TransactionRow( id, status, type, Integer.parseInt( numOfLockedObjects ) ) );
		}

		return list.toArray( new TransactionRow[ list.size() ] );
	}

	private String getTagValue( List<WebElement> elementsList, int index ){
		String value = null;
		if( elementsList != null && elementsList.size() > index ){
			try{
				WebElement webElement = elementsList.get( index );
				WebElement spanCellElement = webElement.findElement( By.tagName( "span" ) );
				value = spanCellElement.getText();
			}
			catch( Exception e ){
				_logger.log( Level.SEVERE, e.toString(), e );
			}
		}

		return value;
	}

}