# multimodule_dishes_app
Multimodule test task

Application consists of 12 modules:
app
ui_categories
ui_dishes
ui_details
ui_cart
datasource_categories
datasource_dishes
datasource_details
datasource_cart
di_module
resourse_module
location_module
delegate_adapter

app consists of main activity that have navigation bar and topbar with date and location provided by 
location_module.

Application have 4 feature-screens, each of them is located in its module (ui_categories, ui_dishes,
ui_details, ui_cart). This feature modules does not depend on each other and share data through
database or MainActicity and FragmentManager (for primitives).

UI feature modules depend on their corresponding datasources modules (datasource_categories,
datasource_dishes, datasource_details, datasource_cart), that contains dataModels and interfaces
that are neccesary for feature modules. This datasource modules can be shared between feature modules.

di_module contains dagger main component with providers of retrofit API and room DB. App depends on 
di_module and provides dependencies from di_module to Dagger Components of feature modules. Dagger
components provide Viewmodel to feature fragments and dependencies to VM.

delegate_adapter contains DelegateAdapter to use in feature modules

resource_module contains drawables, strings etc to be used in other modules

Application uses flow and coroutines for work with server api and DB, Dagger 2 for dependency injection. Architecture pattern is MVVM with CLEAN (partially).

Unfortunately, for now mock server is unavailable, video of the app https://drive.google.com/file/d/1M3tUv1ny6JocAPArdLz1qc8y_8LY90xJ/view?usp=share_link

<img src="https://github.com/Kristalcraft/multimodule_dishes_app/assets/62521232/76500517-0519-4e91-ab6c-19787adf0fc6" width="200" />
<img src="https://github.com/Kristalcraft/multimodule_dishes_app/assets/62521232/92237969-cac8-4c79-8ba9-ec45b520dd3b" width="200" />
<img src="https://github.com/Kristalcraft/multimodule_dishes_app/assets/62521232/ac8817d3-27eb-4ae8-a4e0-6c633c23db8f" width="200" />
<img src="https://github.com/Kristalcraft/multimodule_dishes_app/assets/62521232/b1c5922f-b1a3-4e6c-81cb-f74b97baeb3d" width="200" />
