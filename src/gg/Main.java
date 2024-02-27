package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Main extends Application {
	Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
	
	
	public static Double evaluatePostfixExpression(String postfixExpr) {
	    CurserStack<Double> stack = new CurserStack<Double>();
	    String[] items   = postfixExpr.split(" ");

	    for (String item : items) {
	        try {
	            stack.push(Double.valueOf(item));
	        } catch (NumberFormatException e) {
	            double val1 = 0;
	            double val2 = 0;
	            double res = 0;

	            switch (item) {
	            case "+":
					val1 = stack.pop();
					val2 = stack.pop();
					res = val1 + val2;
					stack.push(res);
					break;
				case "-":
					val1 = stack.pop();
					val2 = stack.pop();
					res = val2 - val1;
					stack.push(res);
					break;
				case "/":
					val1 = stack.pop();
					val2 = stack.pop();
					res = val2 / val1;
					stack.push(res);
					break;
				case "*":
					val1 = stack.pop();
					val2 = stack.pop();
					res = val2 * val1;
					stack.push(res);
					break;
				case "^":
					val1 = stack.pop();
					val2 = stack.pop();
					res = Math.pow(val2,val1);
					stack.push(res);
					break;
				case "%":
					val1 = stack.pop();
					val2 = stack.pop();
					res = val2 % val1;
					stack.push(res);
					break;
				case "sin":
					val1 = stack.pop();
					res = Math.sin(Math.toRadians(val1));
					stack.push(res);
					break;
				case "cos":
					val1 = stack.pop();
					res = Math.cos(Math.toRadians(val1));
					stack.push(res);
					break;
				case "tan":
					val1 = stack.pop();
					res = Math.tan(Math.toRadians(val1));
					stack.push(res);
					break;
				case "!":
					val1 = stack.pop();
					res = fact(val1);
					stack.push(res);
					break;
				case "^ 2":
					val1 = stack.pop();
					res = Math.pow(val1, 2);
					stack.push(res);
					break;
				case "√":
					val1 = stack.pop();
					res = Math.sqrt(val1);
					stack.push(res);
					break;
				case "e":
					val1 = stack.pop();
					res = Math.exp(val1);
					stack.push(res);
					break;
				case "ln":
					val1 = stack.pop();
					res = Math.log(val1);
					stack.push(res);
					break;

				case "log":
					val1 = stack.pop();
					res = Math.log10(val1);
					stack.push(res);
					break;

				case "1/":
					val1 = stack.pop();
					res = 1 / val1;
					stack.push(res);
					break;
				

	            }
	        }
	    }

	    return stack.pop();
	}

	public static String postfix(String x) {

		CurserStack<String> a = new CurserStack <>();
		String y [] =x.split(" ");
		String result = "";
		for(int i = 0 ; i< y.length ; i++) {
			if (y[i].equals("(") ) {
				a.push(y[i]);
			}
			else if (y[i].equals(")")) {
				while (!a.peek().equals("(")) {
				result+=" " +a.peek();
				a.pop();
				}
				a.pop();
			}
		else if (y[i].equals("+")|| y[i].equals("-")) {
				while (!a.isEmpty()&&!a.peek().equals("(")) {
				result +=" " + a.peek();
					a.pop();
				}
				a.push(y[i]);
			} else if(y[i].equals("*")|| y[i].equals("/")|| y[i].equals("%")) {
				if(a.isEmpty()|| a.peek().contains("+")|| a.peek().contains("-")||a.peek().contains("(")  ) {
					a.push(y[i]);
					
				}
				else {
		
						result += " " +a.peek();
							a.pop();
							a.push(y[i]);
			}
			} else if(y[i].equals("^")|| y[i].equals("!")|| y[i].equals("√")) {
					
			a.push(y[i]);
			}
			else if(y[i].contains("sin")|| y[i].contains("cos")|| y[i].contains("log")|| y[i].contains("tan")|| y[i].contains("ln") || y[i].contains("e")) {
				a.push(y[i]);
			}
			else {
				result += " " + y[i];
			}

		}
		while (a.peek() != null) {
			result +=" " + a.peek();
				a.pop();
			}
		return result;
		
	}

	public static double fact(double num) {

		int i, fact = 1;
	
		for (i = 1; i <= num; i++) {
			fact = fact * i;
		}

		return fact;

	}

	private static boolean checkParameters(String exp) {
		boolean res = false;
		CurserStack<Character> s = new CurserStack<>();
		char temp;
		char[] c = exp.toCharArray();

		for (int i = 0; i < c.length; i++) {
			switch (c[i]) {
			case '(':
				s.push(c[i]);
				break;
			case '{':
				s.push(c[i]);
				break;
			case '[':
				s.push(c[i]);
				break;
			case ')':
				if (s.isEmpty() == false) {
					temp = (char) s.pop();
					// System.out.println(temp);
					// System.out.println(c[i]);
					if (temp != '(') {
						// System.out.println("Erorr1");
						return false;
					}
				} else {
					return false;
				}

				break;
			case ']':
				if (s.isEmpty() == false) {
					temp = (char) s.pop();
					if (temp != '[') {
						// System.out.println("Erorr2");
						return false;
					}
				} else {
					return false;
				}

				break;
			case '}':
				if (s.isEmpty() == false) {
					temp = (char) s.pop();
					if (temp != '{') {
						// System.out.println("Erorr3");
						return false;
					}
				} else {
					return false;
				}
				break;
			default:
				break;
			}

		}
		if (s.peek() != null) {
			// System.out.println("Erorr4");
			res = false;
		} else {
			res = true;
		}

		return res;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			
			StackPane st17 = new StackPane();
			Image mh17 = new Image(getClass().getResourceAsStream("moha.jpg"));
			ImageView mah17 = new ImageView(mh17);
			mah17.setFitHeight(1050);
			mah17.setFitWidth(1920);
			
			GridPane root = new GridPane();

			TextField Tx = new TextField();
			Tx.setPrefHeight(25);
			Tx.setPrefWidth(195);
			Tx.setPromptText("Expresion");
			
			TextField Tx1 = new TextField();
			Tx1.setPrefHeight(25);
			Tx1.setPrefWidth(195);
			Tx1.setPromptText("PostFix");
			
			TextField Tx2 = new TextField();
			Tx2.setPrefHeight(25);
			Tx2.setPrefWidth(195);
			Tx2.setPromptText("Result");
			
			Tx.setEditable(false);
			Tx1.setEditable(false);
			Tx2.setEditable(false);
			

			Button but = new Button("sin");
			but.setPrefHeight(100);
			but.setPrefWidth(100);
			but.setTextFill(Color.BLACK);
			but.setFont(Font.font("Oranienbaum", 30));
			but.setContentDisplay(ContentDisplay.TOP);
			but.setId("button");
			but.setStyle("-fx-background-color: blue");
			but.setOnAction(e->{
				Tx.setText(Tx.getText()+""+but.getText()+" ");
			});
		
			
			Button but1 = new Button("cos");
			but1.setPrefHeight(100);
			but1.setPrefWidth(100);
			but1.setTextFill(Color.BLACK);
			but1.setFont(Font.font("Oranienbaum", 30));
			but1.setContentDisplay(ContentDisplay.TOP);
			but1.setId("button");
			root.setMargin(but1, new Insets(0, 0, 0,100));
			but1.setStyle("-fx-background-color: blue");
			but1.setOnAction(e->{
				Tx.setText(Tx.getText()+""+but1.getText()+" ");
			});

			Button but2 = new Button("tan");
			but2.setPrefHeight(100);
			but2.setPrefWidth(100);
			but2.setTextFill(Color.BLACK);
			but2.setFont(Font.font("Oranienbaum", 30));
			but2.setContentDisplay(ContentDisplay.TOP);
			but2.setId("button");
			root.setMargin(but2, new Insets(0, 0, 0,200));
			but2.setStyle("-fx-background-color: blue");
			but2.setOnAction(e->{
				Tx.setText(Tx.getText()+""+but2.getText()+" ");
			});
			
			Button but3 = new Button("(");
			but3.setPrefHeight(100);
			but3.setPrefWidth(100);
			but3.setTextFill(Color.BLACK);
			but3.setFont(Font.font("Oranienbaum", 30));
			but3.setContentDisplay(ContentDisplay.TOP);
			but3.setId("button");
			root.setMargin(but3, new Insets(0, 0, 0,300));
			but3.setStyle("-fx-background-color: wheat");
			but3.setOnAction(e->{
				Tx.setText(Tx.getText()+""+but3.getText()+" ");
			});
			
			
			Button but4 = new Button(")");
			but4.setPrefHeight(100);
			but4.setPrefWidth(100);
			but4.setTextFill(Color.BLACK);
			but4.setFont(Font.font("Oranienbaum", 30));
			but4.setContentDisplay(ContentDisplay.TOP);
			but4.setId("button");
			root.setMargin(but4, new Insets(0, 0, 0,400));
			but4.setStyle("-fx-background-color: wheat");
			but4.setOnAction(e->{
				Tx.setText(Tx.getText()+" "+but4.getText());
			});
			
			Button but5 = new Button("!");
			but5.setPrefHeight(100);
			but5.setPrefWidth(100);
			but5.setTextFill(Color.BLACK);
			but5.setFont(Font.font("Oranienbaum", 30));
			but5.setContentDisplay(ContentDisplay.TOP);
			but5.setId("button");
			root.setMargin(but5, new Insets(0, 0, 0,500));
			but5.setStyle("-fx-background-color: blue");
			but5.setOnAction(e->{
				Tx.setText(Tx.getText()+" "+but5.getText()+" ");
			});

			Button but6 = new Button("OFF");
			but6.setPrefHeight(100);
			but6.setPrefWidth(100);
			but6.setTextFill(Color.BLACK);
			but6.setFont(Font.font("Oranienbaum", 30));
			but6.setContentDisplay(ContentDisplay.TOP);
			but6.setId("button");
			root.setMargin(but6, new Insets(0, 0, 0,600));
			but6.setStyle("-fx-background-color: blue");
			but6.setOnAction(e->{
				primaryStage.close();
			});
			
			
			
			
			Button but7 = new Button("π");
			but7.setPrefHeight(100);
			but7.setPrefWidth(100);
			but7.setTextFill(Color.BLACK);
			but7.setFont(Font.font("Oranienbaum", 30));
			but7.setContentDisplay(ContentDisplay.TOP);
			but7.setId("button");
			but7.setStyle("-fx-background-color: blue");
			but7.setOnAction(e->{
				double π = 3.14159265359;
				Tx.setText(Tx.getText()+""+π+" ");
			});
		
			
			Button but8 = new Button("y^x");
			but8.setPrefHeight(100);
			but8.setPrefWidth(100);
			but8.setTextFill(Color.BLACK);
			but8.setFont(Font.font("Oranienbaum", 30));
			but8.setContentDisplay(ContentDisplay.TOP);
			but8.setId("button");
			root.setMargin(but8, new Insets(0, 0, 0,100));
			but8.setStyle("-fx-background-color: blue");
			but8.setOnAction(e->{
				Tx.setText(Tx.getText()+""+"^"+" ");
			});

			Button but9 = new Button("7");
			but9.setPrefHeight(100);
			but9.setPrefWidth(100);
			but9.setTextFill(Color.DARKRED);
			but9.setFont(Font.font("Oranienbaum", 30));
			but9.setContentDisplay(ContentDisplay.TOP);
			but9.setId("button");
			root.setMargin(but9, new Insets(0, 0, 0,200));
			//but9.setStyle("-fx-background-color: black");
			but9.setOnAction(e->{
				Tx.setText(Tx.getText()+""+but9.getText());
			});
			
			Button but10 = new Button("8");
			but10.setPrefHeight(100);
			but10.setPrefWidth(100);
			but10.setTextFill(Color.DARKRED);
			but10.setFont(Font.font("Oranienbaum", 30));
			but10.setContentDisplay(ContentDisplay.TOP);
			but10.setId("button");
			root.setMargin(but10, new Insets(0, 0, 0,300));
			//but10.setStyle("-fx-background-color: black");
			but10.setOnAction(e->{
				Tx.setText(Tx.getText()+""+but10.getText());
			});
			
			Button but11 = new Button("9");
			but11.setPrefHeight(100);
			but11.setPrefWidth(100);
			but11.setTextFill(Color.DARKRED);
			but11.setFont(Font.font("Oranienbaum", 30));
			but11.setContentDisplay(ContentDisplay.TOP);
			but11.setId("button");
			root.setMargin(but11, new Insets(0, 0, 0,400));
		//	but11.setStyle("-fx-background-color: black");
			but11.setOnAction(e->{
				Tx.setText(Tx.getText()+""+but11.getText());
			});
			
			Button but12 = new Button("÷");
			but12.setPrefHeight(100);
			but12.setPrefWidth(100);
			but12.setTextFill(Color.WHEAT);
			but12.setFont(Font.font("Oranienbaum", 30));
			but12.setContentDisplay(ContentDisplay.TOP);
			but12.setId("button");
			root.setMargin(but12, new Insets(0, 0, 0,500));
			but12.setStyle("-fx-background-color: gray");
			but12.setOnAction(e->{
				Tx.setText(Tx.getText()+" "+"/"+" ");
			});

			Button but13 = new Button("(-)");
			but13.setPrefHeight(100);
			but13.setPrefWidth(100);
			but13.setTextFill(Color.BLACK);
			but13.setFont(Font.font("Oranienbaum", 30));
			but13.setContentDisplay(ContentDisplay.TOP);
			but13.setId("button");
			root.setMargin(but13, new Insets(0, 0, 0,600));
			but13.setStyle("-fx-background-color: wheat");
			but13.setOnAction(e->{
				Tx.setText(Tx.getText()+""+but13.getText());
			});
			
		
			Button but14 = new Button("^ 2");
			but14.setPrefHeight(100);
			but14.setPrefWidth(100);
			but14.setTextFill(Color.BLACK);
			but14.setFont(Font.font("Oranienbaum", 30));
			but14.setContentDisplay(ContentDisplay.TOP);
			but14.setId("button");
			but14.setStyle("-fx-background-color: blue");
			but14.setOnAction(e->{
				Tx.setText(Tx.getText()+" "+but14.getText()+" ");
			});
		
			
			Button but15 = new Button("√");
			but15.setPrefHeight(100);
			but15.setPrefWidth(100);
			but15.setTextFill(Color.BLACK);
			but15.setFont(Font.font("Oranienbaum", 30));
			but15.setContentDisplay(ContentDisplay.TOP);
			but15.setId("button");
			root.setMargin(but15, new Insets(0, 0, 0,100));
			but15.setStyle("-fx-background-color: blue");
			but15.setOnAction(e->{
				Tx.setText(Tx.getText()+" "+but15.getText()+" ");
			});

			Button but16 = new Button("4");
			but16.setPrefHeight(100);
			but16.setPrefWidth(100);
			but16.setTextFill(Color.DARKRED);
			but16.setFont(Font.font("Oranienbaum", 30));
			but16.setContentDisplay(ContentDisplay.TOP);
			but16.setId("button");
			root.setMargin(but16, new Insets(0, 0, 0,200));
		
			but16.setOnAction(e->{
				Tx.setText(Tx.getText()+""+but16.getText());
			});
			
			Button but17 = new Button("5");
			but17.setPrefHeight(100);
			but17.setPrefWidth(100);
			but17.setTextFill(Color.DARKRED);
			but17.setFont(Font.font("Oranienbaum", 30));
			but17.setContentDisplay(ContentDisplay.TOP);
			but17.setId("button");
			root.setMargin(but17, new Insets(0, 0, 0,300));
		
			but17.setOnAction(e->{
				Tx.setText(Tx.getText()+""+but17.getText());
			});
			
			
			Button but18 = new Button("6");
			but18.setPrefHeight(100);
			but18.setPrefWidth(100);
			but18.setTextFill(Color.DARKRED);
			but18.setFont(Font.font("Oranienbaum", 30));
			but18.setContentDisplay(ContentDisplay.TOP);
			but18.setId("button");
			root.setMargin(but18, new Insets(0, 0, 0,400));
		
			but18.setOnAction(e->{
				Tx.setText(Tx.getText()+""+but18.getText());
			});
			
			
			Button but19 = new Button("X");
			but19.setPrefHeight(100);
			but19.setPrefWidth(100);
			but19.setTextFill(Color.BLACK);
			but19.setFont(Font.font("Oranienbaum", 30));
			but19.setContentDisplay(ContentDisplay.TOP);
			but19.setId("button");
			root.setMargin(but19, new Insets(0, 0, 0,500));
			but19.setStyle("-fx-background-color: gray");
			but19.setOnAction(e->{
			
				Tx.setText(Tx.getText()+" "+"*"+" ");
			});
			
			Button but20 = new Button("CA");
			but20.setPrefHeight(100);
			but20.setPrefWidth(100);
			but20.setTextFill(Color.BLACK);
			but20.setFont(Font.font("Oranienbaum", 30));
			but20.setContentDisplay(ContentDisplay.TOP);
			but20.setId("button");
			root.setMargin(but20, new Insets(0, 0, 0,600));
			but20.setStyle("-fx-background-color: #ff3300");
			but20.setOnAction(e->{
				
				Tx.setText("");
				Tx1.setText("");
				Tx2.setText("");
				
			});
			
			
			Button but21 = new Button("e^x");
			but21.setPrefHeight(100);
			but21.setPrefWidth(100);
			but21.setTextFill(Color.BLACK);
			but21.setFont(Font.font("Oranienbaum", 30));
			but21.setContentDisplay(ContentDisplay.TOP);
			but21.setId("button");
			but21.setStyle("-fx-background-color: blue");
			but21.setOnAction(e->{
				Tx.setText(Tx.getText()+" "+"e"+" ");
			});
		
			
			Button but22 = new Button("LN");
			but22.setPrefHeight(100);
			but22.setPrefWidth(100);
			but22.setTextFill(Color.BLACK);
			but22.setFont(Font.font("Oranienbaum", 30));
			but22.setContentDisplay(ContentDisplay.TOP);
			but22.setId("button");
			root.setMargin(but22, new Insets(0, 0, 0,100));
			but22.setStyle("-fx-background-color: blue");
			but22.setOnAction(e->{
				Tx.setText(Tx.getText()+" "+"ln"+" ");
			});

			Button but23 = new Button("1");
			but23.setPrefHeight(100);
			but23.setPrefWidth(100);
			but23.setTextFill(Color.DARKRED);
			but23.setFont(Font.font("Oranienbaum", 30));
			but23.setContentDisplay(ContentDisplay.TOP);
			but23.setId("button");
			root.setMargin(but23, new Insets(0, 0, 0,200));
			
			but23.setOnAction(e->{
				Tx.setText(Tx.getText()+""+but23.getText());
			});
			
			Button but24 = new Button("2");
			but24.setPrefHeight(100);
			but24.setPrefWidth(100);
			but24.setTextFill(Color.DARKRED);
			but24.setFont(Font.font("Oranienbaum", 30));
			but24.setContentDisplay(ContentDisplay.TOP);
			but24.setId("button");
			root.setMargin(but24, new Insets(0, 0, 0,300));
			
			but24.setOnAction(e->{
				Tx.setText(Tx.getText()+""+but24.getText());
			});
			
			
			Button but25 = new Button("3");
			but25.setPrefHeight(100);
			but25.setPrefWidth(100);
			but25.setTextFill(Color.DARKRED);
			but25.setFont(Font.font("Oranienbaum", 30));
			but25.setContentDisplay(ContentDisplay.TOP);
			but25.setId("button");
			root.setMargin(but25, new Insets(0, 0, 0,400));
			
			but25.setOnAction(e->{
				Tx.setText(Tx.getText()+""+but25.getText());
			});
			
			Button but26 = new Button("-");
			but26.setPrefHeight(100);
			but26.setPrefWidth(100);
			but26.setTextFill(Color.BLACK);
			but26.setFont(Font.font("Oranienbaum", 30));
			but26.setContentDisplay(ContentDisplay.TOP);
			but26.setId("button");
			root.setMargin(but26, new Insets(0, 0, 0,500));
			but26.setStyle("-fx-background-color: gray");
			but26.setOnAction(e->{
				Tx.setText(Tx.getText()+" "+but26.getText()+" ");
			});

			Button but27 = new Button("C");
			but27.setPrefHeight(100);
			but27.setPrefWidth(100);
			but27.setTextFill(Color.BLACK);
			but27.setFont(Font.font("Oranienbaum", 30));
			but27.setContentDisplay(ContentDisplay.TOP);
			but27.setId("button");
			root.setMargin(but27, new Insets(0, 0, 0,600));
			but27.setStyle("-fx-background-color: #ff3300");
			but27.setOnAction(e->{
			
			});
			
			
			Button but28 = new Button("LOG");
			but28.setPrefHeight(100);
			but28.setPrefWidth(100);
			but28.setTextFill(Color.BLACK);
			but28.setFont(Font.font("Oranienbaum", 30));
			but28.setContentDisplay(ContentDisplay.TOP);
			but28.setId("button");
			but28.setStyle("-fx-background-color: blue");
			but28.setOnAction(e->{
				Tx.setText(Tx.getText()+" "+"log"+" ");
			});
		
			
			Button but29 = new Button("1/X");
			but29.setPrefHeight(100);
			but29.setPrefWidth(100);
			but29.setTextFill(Color.BLACK);
			but29.setFont(Font.font("Oranienbaum", 30));
			but29.setContentDisplay(ContentDisplay.TOP);
			but29.setId("button");
			root.setMargin(but29, new Insets(0, 0, 0,100));
			but29.setStyle("-fx-background-color: blue");
			but29.setOnAction(e->{
				Tx.setText(Tx.getText()+" "+"1 /"+" ");
			
			});

			Button but30 = new Button("0");
			but30.setPrefHeight(100);
			but30.setPrefWidth(100);
			but30.setTextFill(Color.DARKRED);
			but30.setFont(Font.font("Oranienbaum", 30));
			but30.setContentDisplay(ContentDisplay.TOP);
			but30.setId("button");
			root.setMargin(but30, new Insets(0, 0, 0,200));
		
			but30.setOnAction(e->{
				Tx.setText(Tx.getText()+""+but30.getText());
			});
			
			Button but31 = new Button(".");
			but31.setPrefHeight(100);
			but31.setPrefWidth(100);
			but31.setTextFill(Color.BLACK);
			but31.setFont(Font.font("Oranienbaum", 30));
			but31.setContentDisplay(ContentDisplay.TOP);
			but31.setId("button");
			root.setMargin(but31, new Insets(0, 0, 0,300));
			but31.setStyle("-fx-background-color: wheat");
			but31.setOnAction(e->{
			
				Tx.setText(Tx.getText().concat("."));
				
				
				
				
				
			});
			
			Button but32 = new Button("%");
			but32.setPrefHeight(100);
			but32.setPrefWidth(100);
			but32.setTextFill(Color.BLACK);
			but32.setFont(Font.font("Oranienbaum", 30));
			but32.setContentDisplay(ContentDisplay.TOP);
			but32.setId("button");
			root.setMargin(but32, new Insets(0, 0, 0,400));
			but32.setStyle("-fx-background-color: gray");
			but32.setOnAction(e->{
				Tx.setText(Tx.getText()+" "+but32.getText()+" ");
			});
			
			Button but33 = new Button("+");
			but33.setPrefHeight(100);
			but33.setPrefWidth(100);
			but33.setTextFill(Color.BLACK);
			but33.setFont(Font.font("Oranienbaum", 30));
			but33.setContentDisplay(ContentDisplay.TOP);
			but33.setId("button");
			root.setMargin(but33, new Insets(0, 0, 0,500));
			but33.setStyle("-fx-background-color: gray");
			but33.setOnAction(e->{
				Tx.setText(Tx.getText()+" "+but33.getText()+" ");
			});

			Button but34 = new Button("<--");
			but34.setPrefHeight(100);
			but34.setPrefWidth(100);
			but34.setTextFill(Color.BLACK);
			but34.setFont(Font.font("Oranienbaum", 30));
			but34.setContentDisplay(ContentDisplay.TOP);
			but34.setId("button");
			root.setMargin(but34, new Insets(0, 0, 0,600));
			but34.setStyle("-fx-background-color: #ff3300");
			but34.setOnAction(e->{
				String string = Tx.getText();
				Tx.setText("");
				for(int i =0 ; i<string.length()-1 ; i++) {
					Tx.setText(Tx.getText()+string.charAt(i));
				}
				
			
			});
			
		
			Button but35 = new Button("=");
			but35.setPrefHeight(100);
			but35.setPrefWidth(700);
			but35.setTextFill(Color.BLACK);
			but35.setFont(Font.font("Oranienbaum", 30));
			but35.setContentDisplay(ContentDisplay.TOP);
			but35.setId("button");
			but35.setStyle("-fx-background-color: blue");
			
			
			
				
			
			but35.setOnAction(e->{
				try {
				if(checkParameters(Tx.getText()) == true) {
				
				Tx1.setText(postfix(Tx.getText()));
				Tx2.setText(String.valueOf(evaluatePostfixExpression(Tx1.getText())));
				}else {
					Tx2.setText("Missing parentheses");
					Tx1.setText("");
				}
				
			}catch (NullPointerException m) {
				m.getMessage();
				Tx2.setText("Null Pointer");
			}
			
			
			});
			
			
			root.add(Tx, 1, 0);
			root.add(Tx1, 1, 1);
			root.add(Tx2, 1, 2);
			
			
			root.add(but, 1, 3);
			root.add(but1, 1, 3);
			root.add(but2, 1, 3);
			root.add(but3, 1, 3);
			root.add(but4, 1, 3);
			root.add(but5, 1, 3);
			root.add(but6, 1, 3);
			
			root.add(but7, 1, 4);
			root.add(but8, 1, 4);
			root.add(but9, 1, 4);
			root.add(but10, 1, 4);
			root.add(but11, 1, 4);
			root.add(but12, 1, 4);
			root.add(but13, 1, 4);
			
			root.add(but14, 1, 5);
			root.add(but15, 1, 5);
			root.add(but16, 1, 5);
			root.add(but17, 1, 5);
			root.add(but18, 1, 5);
			root.add(but19, 1, 5);
			root.add(but20, 1, 5);
			
			root.add(but21, 1, 6);
			root.add(but22, 1, 6);
			root.add(but23, 1, 6);
			root.add(but24, 1, 6);
			root.add(but25, 1, 6);
			root.add(but26, 1, 6);
			root.add(but27, 1, 6);
			
			root.add(but28, 1, 7);
			root.add(but29, 1, 7);
			root.add(but30, 1, 7);
			root.add(but31, 1, 7);
			root.add(but32, 1, 7);
			root.add(but33, 1, 7);
			root.add(but34, 1, 7);
			
			root.add(but35, 1, 8);
			
			root.setAlignment(Pos.CENTER);
		
			
			st17.getChildren().addAll(mah17, root);
			Scene scene = new Scene(st17, 800, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}