//
//  DatabaseSingleton.swift
//  moviesMp
//
//  Created by Training Sj on 10/26/23.
//  Copyright © 2023 orgName. All rights reserved.
//
//
import Foundation
import CoreData

class DataController: ObservableObject {
    let container = NSPersistentContainer(name: "Model")
    init(){
        container.loadPersistentStores { description, error in
            if let error = error {
                print("Core data failed to load \(error.localizedDescription)")
            }
        }
    }
}

