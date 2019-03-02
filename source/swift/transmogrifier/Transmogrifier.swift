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

import Foundation

/// Runs a processor on an optional input and extra to produce an output.
class Transmogrifier
{
    /// Perform the transformation, taking the input and extra and creating an output.
    ///
    /// - Parameters:
    ///   - input: the input parameter
    ///   - extra: the extra parameter
    ///   - processor: the processor to use in the transformation
    /// - Returns: the result of the transformation
    /// - Throws: if something goes wrong with the processing
    public func transform<I, E, O>(on input: I?, and extra: E?, with processor : Processor<I, E, O>) throws -> O?
    {
        let output = try processor.perform(on: input, and: extra)
        
        return output!
    }
    
    /// <#Description#>
    ///
    /// - Parameters:
    ///   - input: the input parameter
    ///   - processor: the processor to use in the transformation
    /// - Returns: the result of the transformation
    /// - Throws: if something goes wrong with the processing
    public func transform<I, Void, O>(on input: I?, with processor : Processor<I, Void, O>) throws -> O?
    {
        let output = try processor.perform(on: input)
        
        return output!
    }
}
