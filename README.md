
# MultiSelectPopWindow

## [中文文档](https://github.com/Jay-Goo/MultiSelectPopWindow/blob/master/README_ZH.md)


![image](https://github.com/Jay-Goo/MultiSelectPopWindow/blob/master/preview/2017-02-23%2010_25_46.gif)



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

#Feature
more attributes you could set !


##Others 

I hope you like this MultiSelectPopWindow. `Star` is the greatest support for me！ Thank U




