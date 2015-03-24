## Wideroid Android Helper Framework

### Synopsis
A bunch of helpers and annotations to help you write less and do more, mainly designed to be used in Android development.

### Quick Start

#### ViewReference Annotation

```Java
public class MainActivity extends Activity {
  
  @ViewReference
  private TextView textViewHelloWorld;
  
  @ViewReference(viewId = "buttonOK")
  private Button buttonChangeText;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    Wideroid.processActivity(this);
    
    buttonChangeText.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        textViewHelloWorld.setText("Text Changed!");
      }
    }
  }
}
```
#### OnClick Annotation

```Java
public class MainActivity extends Activity {
  
  @OnClick(methodName = "changeText")
  @ViewReference(viewId = "buttonChangeText")
  private Button changeTextButton;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    Wideroid.processActivity(this);
  }
  
  public void changeText(View view) {
    textViewHelloWorld.setText("Text Changed!");
  }
  
  @OnClick(viewId = "buttonMakeToastMessage")
  public void makeToastMessage(View view) {
    Toast.makeText(this, "Hello World from Toast ...", Toast.LENGTH_LONG).show();
  }
}
```

### Contributors
1. [Kamran Amini](https://github.com/kamcpp)

### License

**Wideroid** is free, open source and redistributable under [Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
