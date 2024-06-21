import database.AppDatabase

expect class AppFactory {

    fun getDatabase(): AppDatabase
}

expect val appFactoryInstance: AppFactory
