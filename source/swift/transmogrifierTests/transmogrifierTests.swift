/*
 * Copyright (C) 2019 Terratap Technology Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import XCTest
@testable import transmogrifier

class UppercaseProcessor
    : Processor<String, Void, String>
{
    override func perform(on input: String?, and extra : Void?) throws -> String?
    {
        return input!.uppercased()
    }
}

class transmogrifierTests: XCTestCase
{
    func testExample()
        throws
    {
        let transmogrifier = Transmogrifier()
        let processor = UppercaseProcessor()
        var string : String
        
        string = try transmogrifier.transform(on: "Hello", and: nil, with: processor)!
        XCTAssertEqual("HELLO", string)
        
        string = try transmogrifier.transform(on: "worlD", with: processor)!
        XCTAssertEqual("WORLD", string)
    }
}
