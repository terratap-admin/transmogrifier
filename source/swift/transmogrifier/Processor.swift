//
//  Processor.swift
//  transmogrifier
//
//  Created by D’Arcy Smith on 2019-03-01.
//  Copyright © 2019 Terratap Technologies Inc. All rights reserved.
//

import Foundation

class Processor<I, E, O>
{
    func perform(on input: I?, and extra : E?) throws -> O?
    {
        preconditionFailure("This method must be overridden")
    }
}
