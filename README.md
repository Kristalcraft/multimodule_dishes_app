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
