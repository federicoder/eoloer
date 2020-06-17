import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { ConsuntivoTaskComponent } from 'app/entities/consuntivo-task/consuntivo-task.component';
import { ConsuntivoTaskService } from 'app/entities/consuntivo-task/consuntivo-task.service';
import { ConsuntivoTask } from 'app/shared/model/consuntivo-task.model';

describe('Component Tests', () => {
  describe('ConsuntivoTask Management Component', () => {
    let comp: ConsuntivoTaskComponent;
    let fixture: ComponentFixture<ConsuntivoTaskComponent>;
    let service: ConsuntivoTaskService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [ConsuntivoTaskComponent],
      })
        .overrideTemplate(ConsuntivoTaskComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConsuntivoTaskComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConsuntivoTaskService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ConsuntivoTask(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.consuntivoTasks && comp.consuntivoTasks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
