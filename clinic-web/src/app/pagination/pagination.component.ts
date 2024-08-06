import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { PageEvent, MatPaginatorModule } from '@angular/material/paginator';

@Component({
  selector: 'app-pagination',
  standalone: true,
  imports: [CommonModule, MatPaginatorModule],
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.scss'
})
export class PaginationComponent {
  @Input() page: number = 0;
  @Output() pageChange = new EventEmitter<any>();

  @Input() size: number = 10;
  @Input() length: number = 0;


  constructor() {
    
  }

  handlePageEvent(event: PageEvent) {
    this.page = event.pageIndex;
    this.pageChange.emit(this.page);
  }
  
}
