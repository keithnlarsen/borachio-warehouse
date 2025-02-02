package com.touchtype_fluency.examples.borachio_warehouse.test;

import junit.framework.TestCase
import junit.framework.Assert._
import android.test.suitebuilder.annotation._

import com.borachio._
import com.borachio.junit3.MockFactory

import com.google.inject._
import com.touchtype_fluency.examples.borachio_warehouse._

class TestBorachioWarehouse extends TestCase with MockFactory {

    var _injector : Injector = _
    var _mockWarehouse : IWarehouse with Mock = _

    override def setUp() {
        super.setUp()
        _mockWarehouse = mock[IWarehouse]

        _injector = Guice.createInjector(new AbstractModule {
            override def configure() {
                // System Under Test is Order, so we use the real implementation
                bind(classOf[IOrder]).to(classOf[Order])
                // Order has a dependency on Warehouse, which we are mocking
                bind(classOf[IWarehouse]).toInstance(_mockWarehouse)
            }
        })
    }

    @MediumTest
    def testFill() {
        withExpectations {
            inSequence {
                _mockWarehouse expects 'hasInventory withArgs ("Talisker", 10) returning true  once();
                _mockWarehouse expects 'remove withArgs ("Talisker", 10) once();
            }

            val o = _injector.getInstance(classOf[IOrder])
            o.update("Talisker", 10)

            assertTrue(o.fill)
            verifyExpectations()
        }
    }

    @MediumTest
    def testFill_NoInventory() {
        withExpectations {
            inSequence {
                _mockWarehouse expects 'hasInventory withArgs ("Talisker", 10) returning false once();
                _mockWarehouse expects 'remove withArgs ("Talisker", 10) never();
            }

            val o = _injector.getInstance(classOf[IOrder])
            o.update("Talisker", 10)

            assertFalse(o.fill)
            verifyExpectations()
        }
    }

    override def tearDown() {
        super.tearDown()
    }
}
