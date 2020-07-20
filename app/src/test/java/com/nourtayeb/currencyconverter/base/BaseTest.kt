package com.nourtayeb.currencyconverter.base



import android.content.Context
import com.nourtayeb.currencyconverter.common.API_BASE_URL
import com.nourtayeb.currencyconverter.common.di.ViewModelFactory
import com.nourtayeb.currencyconverter.common.di.modules.*
import com.nourtayeb.currencyconverter.di.modules.TestRxJavaModule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import java.io.File
import javax.inject.Inject
import kotlin.test.AfterTest

abstract class BaseTest {

    lateinit var testAppComponent: TestAppComponent
    lateinit var mockServer: MockWebServer
    @Inject lateinit var viewModelFactory: ViewModelFactory


    abstract fun getMockContext(): Context
    @Before
    open fun setUp(){
        this.configureMockServer()
        this.configureDi()
    }

    @AfterTest
    open fun tearDown(){
        this.stopMockServer()
    }

    // CONFIGURATION
    open fun configureDi(){
        try {
            this.testAppComponent = DaggerTestAppComponent.builder()
                .netModule(NetModule(if (isMockServerEnabled()) mockServer.url("/").toString() else API_BASE_URL,getMockContext()))
                .mapperModule(MapperModule())
                .localeStorageModule(LocaleStorageModule(getMockContext()))
                .repositoryModule(RepositoryModule())
                .testRxJavaModule(TestRxJavaModule())
                .build()
            this.testAppComponent.inject(this)
        }catch (e:UninitializedPropertyAccessException){
            throw IllegalArgumentException("Your getMockContext() should be initiated before you call super.setUp()")
        }
    }


    // MOCK SERVER
    abstract fun isMockServerEnabled(): Boolean // Because we don't want it always enabled on all tests

    open fun configureMockServer(){
        if (isMockServerEnabled()){
            mockServer = MockWebServer()
            mockServer.start()
        }
    }

    open fun stopMockServer() {
        if (isMockServerEnabled()){
            mockServer.shutdown()
        }
    }

    open fun mockHttpResponse(fileName: String, responseCode: Int) = mockServer.enqueue(MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJson(fileName)))

    private fun getJson(path : String) =
        if(this.javaClass.classLoader!=null) {
            val uri = this.javaClass.classLoader!!.getResource(path)
            val file = File(uri.path)
            String(file.readBytes())
        }else {
             "";
        }
    }

