package com.luoshunhao.cal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private Button[] NumButtons = new Button[10];
	private List<Button> ButtonList = new ArrayList<Button>(16);
	private Calculator calc ;
	private int[] ButtonIDs = new int[]{
			R.id.num0,
			R.id.num1,
			R.id.num2,
			R.id.num3,
			R.id.num4,
			R.id.num5,
			R.id.num6,
			R.id.num7,
			R.id.num8,
			R.id.num9,	
	};
	private Button ButtonAdd ;
	private Button ButtonSub ;
	private Button ButtonMul ;
	private Button ButtonDiv ;
	private Button ButtonClean ;
	private Button ButtonEqu ;
	
	private EditText Edit ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		FindView();	
		for(Button but : ButtonList){
			but.setOnClickListener(new btnOnclickListtenner());
		}
		ButtonEqu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String input = Edit.getText().toString().trim();
				calc = new Calculator(input);
//				System.out.println(Edit.getText().toString().trim());
				Edit.setText(input+"="+calc.GetResult());
				System.out.println(calc.getSuffix());
				
			}
		});
	}
	/*
	 * 获取界面上的控件
	 */
	private void FindView(){
		Edit = (EditText)findViewById(R.id.editview);
		for(int i = 0 ; i < NumButtons.length ; i++){
			NumButtons[i] = (Button)findViewById(ButtonIDs[i]);
		}
		Collections.addAll(ButtonList, NumButtons);
		
		ButtonAdd = (Button)findViewById(R.id.add);
		Collections.addAll(ButtonList, ButtonAdd);
		
		ButtonSub = (Button)findViewById(R.id.sub);
		Collections.addAll(ButtonList, ButtonSub);
		
		ButtonMul = (Button)findViewById(R.id.mul);
		Collections.addAll(ButtonList, ButtonMul);
		
		ButtonDiv = (Button)findViewById(R.id.div);
		Collections.addAll(ButtonList, ButtonDiv);
		
		ButtonClean = (Button)findViewById(R.id.clean);
		Collections.addAll(ButtonList, ButtonClean);
		
		ButtonEqu = (Button)findViewById(R.id.equ);
		Collections.addAll(ButtonList, ButtonEqu);
		
		
	}
	/*
	 * 按键监听器
	 */
	 class btnOnclickListtenner implements OnClickListener
	   {
		  
			@Override
			public void onClick(View v) 
			{
				StringBuilder Input = new StringBuilder(Edit.getText().toString());
				switch (v.getId()) {
				case R.id.num0:
					Input.append("0");
					break;
				case R.id.num1:
					Input.append("1");
					break;
				case R.id.num2:
					Input.append("2");
					break;
				case R.id.num3:
					Input.append("3");
					break;
				case R.id.num4:
					Input.append("4");
					break;
				case R.id.num5:
					Input.append("5");
					break;
				case R.id.num6:
					Input.append("6");
					break;
				case R.id.num7:
					Input.append("7");
					break;
				case R.id.num8:
					Input.append("8");
					break;
				case R.id.num9:
					Input.append("9");
					break;
				case R.id.add:
					Input.append("+");
					break;
				case R.id.sub:
					Input.append("-");
					break;
				case R.id.mul:
					Input.append("*");
					break;
				case R.id.div:
					Input.append("/");
					break;
				case R.id.clean:
					if(!TextUtils.isEmpty(Edit.getText())){
//						Input.deleteCharAt(Input.length()-1);	
						Input.delete(0, Input.length());
					}
					break;
				default:
					break;
				}
				Edit.setText(Input.toString());
			}

	   }
}
