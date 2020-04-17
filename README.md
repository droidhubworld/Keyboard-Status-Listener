# Keyboard-Status-Listener
Android libraries for check keyboard status is open or closed

###### Step 1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
   repositories {
     ...
     maven { url 'https://jitpack.io' }
    }
   }
]
```
 ###### Step 2. Add the dependency
 ```
dependencies {
        implementation 'com.github.Anandkumar961:Keyboard-Status-Listener:0.1.0'
}
```
###### On Activity Create
```
 KeyboardEventListener keyboardEventListener;
 
 keyboardEventListener = new KeyboardEventListener(this, rootView, this);

```
###### onPause
```
if (keyboardEventListener != null) {
    keyboardEventListener.unregisterKeyboardListener();
}

```
###### Override Keyboard Status Listener
```
@Override
public void onKeyboardStatusChange(Boolean isOpen) {
    if (isOpen) {
        Log.e(TAG, "keyboard show");
        Toast.makeText(this, "keyboard show", Toast.LENGTH_SHORT).show();
    } else {
        Log.e(TAG, "keyboard hide");
        Toast.makeText(this, "keyboard hide", Toast.LENGTH_SHORT).show();
    }
}
 ```
  
  ## All Done
