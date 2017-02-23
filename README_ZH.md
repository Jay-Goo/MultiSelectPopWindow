
# MultiSelectPopWindow

## [中文文档]()


![image]()



##Usage
###Step1：
```xml
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	dependencies {
	        compile 'com.github.Jay-Goo:MultiSelectPopWindow:v1.0.0'
	}

```


###Step2：
```xml
   new MultiSelectPopWindow.Builder(this)
                           .setNameArray(names)
                           .setConfirmListener(new MultiSelectPopWindow.OnConfirmClickListener() {
                               @Override
                               public void onClick(ArrayList<Integer> indexList, ArrayList<String> selectedList) {
                               //do something
                               }
                           })
                           .setCancel("取消")
                           .setConfirm("完成")
                           .setTitle("班级列表")
                           .build()
                           .show(findViewById(R.id.mBottom));
```

##其它
希望你喜欢我的作品。`Star`是对我的最大支持. 谢谢




