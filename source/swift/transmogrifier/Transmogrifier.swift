//
//  Transmogrifier.swift
//  transmogrifier
//
//  Created by D’Arcy Smith on 2019-03-01.
//  Copyright © 2019 Terratap Technologies Inc. All rights reserved.
//

import Foundation

class Transmogrifier
{
    public func transform<I, E, O>(on input: I?, and extra: E?, with processor : Processor<I, E, O>) throws -> O?
    {
        let output = try processor.perform(on: input, and: extra)
        
        return output!
    }
}
