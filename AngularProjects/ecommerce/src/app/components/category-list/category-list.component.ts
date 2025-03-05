import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css'],
})
export class CategoryListComponent {
  categories: string[] = ['Laptops', 'Mobiles', 'Shirts', 'Beauty'];

  @Output() categorySelected = new EventEmitter<string>();

  selectCategory(category: string) {
    this.categorySelected.emit(category);
  }
}
