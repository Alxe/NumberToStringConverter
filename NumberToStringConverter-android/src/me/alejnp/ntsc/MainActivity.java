package me.alejnp.ntsc;

import java.text.NumberFormat;

import me.alejnp.ntsc.converter.ConverterFactory;
import me.alejnp.ntsc.exception.UnsupportedLanguageException;
import me.alejnp.ntsc.interfaces.IConverter;
import me.alejnp.ntsc.loader.DataLoader;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private abstract class AbstractOnClickListener implements OnClickListener {
		@Override
		public final void onClick(View v) {
			try {
				if(ntsc == null) updateConverter();
				
				doSomething(v);
				reConvert();
			} catch(NumberFormatException e) {
//				Toast.makeText(getApplicationContext(), R.string.integer_out_of_range, Toast.LENGTH_SHORT).show();
			}
		}
		
		private final void reConvert() {
			String number = NumberFormat.getInstance().format(accumulator),
					word = ntsc.convert(accumulator.intValue());
			
			lblNumber.setText(number);
			lblString.setText(word);
		}
		
		public abstract void doSomething(View v);
	}
	private final class AddNumberOnclickListener extends AbstractOnClickListener {
		private final int value;
		
		public AddNumberOnclickListener(int value) {
			this.value = value;
		}
		
		@Override
		public void doSomething(View v) {
			addToAccumulator(value);
		}
	}
	
	private TextView lblNumber, lblString;
	
	private Button btnZero, btnOne, btnTwo, btnThree,
		btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine,
		btnReset, btnSign;
	
	private IConverter ntsc;
	
	private Long accumulator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		updateReferences();
		setDefaultValues();
		setListeners();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void updateConverter() {
		DataLoader dl = new DataLoader(getApplicationContext());
		ConverterFactory cf = ConverterFactory.getFactory(dl);
		
		try {
			ntsc = cf.getConverter(getString(R.string.lang));
		} catch (UnsupportedLanguageException e) {
			Toast.makeText(getApplicationContext(), R.string.converter_not_found, Toast.LENGTH_SHORT).show();
		}
		
	}

	private void setDefaultValues() {
		lblNumber.setText(R.string.empty_number);
		lblString.setText(R.string.empty_string);
		
	}

	private void setListeners() {
		btnZero.setOnClickListener(new AddNumberOnclickListener(0));
		btnOne.setOnClickListener(new AddNumberOnclickListener(1));
		btnTwo.setOnClickListener(new AddNumberOnclickListener(2));
		btnThree.setOnClickListener(new AddNumberOnclickListener(3));
		btnFour.setOnClickListener(new AddNumberOnclickListener(4));
		btnFive.setOnClickListener(new AddNumberOnclickListener(5));
		btnSix.setOnClickListener(new AddNumberOnclickListener(6));
		btnSeven.setOnClickListener(new AddNumberOnclickListener(7));
		btnEight.setOnClickListener(new AddNumberOnclickListener(8));
		btnNine.setOnClickListener(new AddNumberOnclickListener(9));
		
		btnReset.setOnClickListener(new AbstractOnClickListener() {
			
			@Override
			public void doSomething(View v) {
				accumulator = 0L;
			}
		});
		
		btnSign.setOnClickListener(new AbstractOnClickListener() {
			
			@Override
			public void doSomething(View v) {
				accumulator = -accumulator;
			}
		});
		
	}

	private void updateReferences() {
		btnZero = (Button) findViewById(R.id.btnZero);
		btnOne = (Button) findViewById(R.id.btnOne);
		btnTwo = (Button) findViewById(R.id.btnTwo);
		btnThree = (Button) findViewById(R.id.btnThree);
		btnFour = (Button) findViewById(R.id.btnFour);
		btnFive = (Button) findViewById(R.id.btnFive);
		btnSix = (Button) findViewById(R.id.btnSix);
		btnSeven = (Button) findViewById(R.id.btnSeven);
		btnEight = (Button) findViewById(R.id.btnEight);
		btnNine = (Button) findViewById(R.id.btnNine);
		btnSign = (Button) findViewById(R.id.btnSign);
		btnReset = (Button) findViewById(R.id.btnReset);
		
		lblNumber = (TextView) findViewById(R.id.lblNumber);
		lblString = (TextView) findViewById(R.id.lblString);
		
		accumulator = 0L;
	}

	private void addToAccumulator(int n) {
		long temp = (accumulator * 10) + n;
		
		if(temp <= 999999999) { accumulator = temp; }
	}

}
