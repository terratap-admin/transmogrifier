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

/// A processor takes input and an extra for arguments, processes them, and generates an output.
class Processor<I, E, O>
{
    /// <#Description#>Perform the process, taking the input and extra and creating an output.
    ///
    /// - Parameters:
    ///   - input: the input parameter
    ///   - extra: the extra parameter
    /// - Returns: the result
    /// - Throws: if something goes wrong with the processing
    func perform(on input: I?, and extra : E?) throws -> O?
    {
        preconditionFailure("This method must be overridden")
    }
    
    /// Perform the process, taking the input, using null for the extra, and creating an output.
    ///
    /// - Parameter input: the input parameter
    /// - Returns: the result
    /// - Throws: if something goes wrong with the processing
    func perform(on input: I?) throws -> O?
    {
        return try perform(on: input, and: nil)
    }
}
