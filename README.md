## The Net Circle internship test
An Android app that will retrieve 30 images from dribbble's API and display them in a ``RecyclerView``.

``SwipeRefreshLayout`` is implemented to achieve "pull to refresh" feature.

![Screenshot](https://raw.githubusercontent.com/mingxuanM/InternshipTest/tree/master/screenshots/Screenshot_1509508821.png)
[[https://raw.githubusercontent.com/mingxuanM/InternshipTest/tree/master/screenshots/Screenshot_1509508821.png|alt=octocat]]

### Structure:
* **MainActivity.java**: The main class that create the Views and call the AsyncTask.
* **RecyclerViewAdapter.java**: The ViewAdapter which contains a ViewHolder that will bind images with imageViews and inflate them.
* **GetJson.java**: The AsyncTask class that access the API and parse JSON to get the images' URLs.

* **activity_main.xml**: The main view group of the app which contains the ``SwipeRefreshLayout`` and ``RecyclerView``.
* **image.xml**: The view group for displaing a single image which contains a ``ImageView``. 
