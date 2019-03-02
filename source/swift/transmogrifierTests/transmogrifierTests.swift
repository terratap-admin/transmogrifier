//
//  transmogrifierTests.swift
//  transmogrifierTests
//
//  Created by D’Arcy Smith on 2019-03-01.
//  Copyright © 2019 Terratap Technologies Inc. All rights reserved.
//

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
        let string : String
        
        string = try transmogrifier.transform(on: "Hello", and: nil, with: processor)!
        XCTAssertEqual("HELLO", string)
    }
}
