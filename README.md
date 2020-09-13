# Android Client for the Contacts Book API

Example using best practices:
* MVVM (using ViewModel and Livedata)
* Two-way databinding
* Dependency Injection (Dagger)
* API Rest calls (Retrofit)
* Reactive

## Instructions

Clone the repository and run on your IDE.

The application can be run in two modes: using an in-memory api or using the real api. All of these is controlled by dependency injeccion.

To use the in memory mode, just change the value of USE_IN_MEMORY_API to true. It can be found on class Constants on the model package.

To use the real Api. Follow the instructions at https://github.com/carlosggz/ContactsBookDotNetCore, and set the USE_IN_MEMORY_API to false. Please, verify the value of CONTACTS_API_URL. If you run it locally without any change to te api, it contains the correct url.

### TODO

* Tests
* Local cache using Room
* Settings menu to set the values of the constants
