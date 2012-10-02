/*******************************************************************************
* Copyright (c) 2012 GigaSpaces Technologies Ltd. All rights reserved
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
******************************************************************************/
package test.cli.cloudify.cloud;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

//import framework.utils.AssertUtils;
import framework.utils.AssertUtils;
import framework.utils.LogUtils;

public class MyAbstractClass {
	
	@BeforeMethod
	public void beforeMethod() {
		LogUtils.log("in the before method");
	}
	
	@AfterTest
	public void afterTest() {
		LogUtils.log("in the after test");
		//AssertUtils.AssertFail("Failing the after test");	
	}
	
	@AfterClass
	public void afterClass() {
		LogUtils.log("in the after class");
		AssertUtils.AssertFail("Failing the after class");	
	}
	
	@AfterMethod
	public void afterMethod() {
		LogUtils.log("in the after method");
		//AssertUtils.AssertFail("Failing the after method");
	}

}
