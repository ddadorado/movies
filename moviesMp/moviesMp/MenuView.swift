//
//  MenuView.swift
//  moviesMp
//
//  Created by Training Sj on 10/24/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import AsyncImage
import shared


//
//  testTabView.swift
//  moviesMp
//
//  Created by Training Sj on 10/24/23.
//  Copyright © 2023 orgName. All rights reserved.
//


struct MenuView: View {
    
    @State var selectedTab = Tabs.FirstTab
    @StateObject var viewModel: MoviesViewModel = MoviesViewModel()
    var body: some View {
        
        VStack {
            HStack {
                Spacer()
                VStack {
                    Image(systemName: "film")
                    .foregroundColor(selectedTab == .FirstTab ? Color.red : Color.black)
                    Text("All Movies")
                }
                .onTapGesture {
                    self.selectedTab = .FirstTab
                }
                Spacer()
                VStack {
                    Image(systemName: "heart")
                        .foregroundColor(selectedTab == .SecondTab ? Color.red : Color.black)
                    Text("Favorite Movies")
                }
                .onTapGesture {
                    self.selectedTab = .SecondTab
                }
                Spacer()
                VStack {
                    Image(systemName: "cart.fill")
                        .foregroundColor(selectedTab == .ThirdTab ? Color.red : Color.black)
                    Text("Exit")
                }
                .onTapGesture {
                    self.selectedTab = .ThirdTab
                }
                Spacer()
            }
            .padding(.bottom)
            .background(Color.green.edgesIgnoringSafeArea(.all))
            
            //Spacer()
            
            if selectedTab == .FirstTab {
                AllMoviesView(viewModel: viewModel)
            } else if selectedTab == .SecondTab {
                FavoriteMoviesView(viewModel: viewModel)
            } else {
                ThirdTabView()
            }
        }.navigationBarBackButtonHidden(true)
    }
}








struct ThirdTabView : View {
    
    var body : some View {
        
        Text("THIRD TAB VIEW")

    }
}

enum Tabs {
    case FirstTab
    case SecondTab
    case ThirdTab
}






// Example usage

// Class

