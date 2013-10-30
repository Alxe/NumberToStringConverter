package me.alejnp.numbertostringconverter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final class AddNumberOnclickListener implements OnClickListener {
		private final int value;
		
		public AddNumberOnclickListener(int value) {
			this.value = value;
		}
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	}

	private TextView lblNumber, lblString;
	private Button btnZero, btnOne, btnTwo, btnThree,
		btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine,
		btnReset, btnDot;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		updateReferences();
		setListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void updateReferences() {
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
		btnDot = (Button) findViewById(R.id.btnDot);
		btnReset = (Button) findViewById(R.id.btnReset);
		
	}

	public void setListeners() {
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
		
		btnReset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnDot.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
}
