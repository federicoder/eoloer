import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { ConsuntivoTaskDetailComponent } from 'app/entities/consuntivo-task/consuntivo-task-detail.component';
import { ConsuntivoTask } from 'app/shared/model/consuntivo-task.model';

describe('Component Tests', () => {
  describe('ConsuntivoTask Management Detail Component', () => {
    let comp: ConsuntivoTaskDetailComponent;
    let fixture: ComponentFixture<ConsuntivoTaskDetailComponent>;
    const route = ({ data: of({ consuntivoTask: new ConsuntivoTask(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [ConsuntivoTaskDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ConsuntivoTaskDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ConsuntivoTaskDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load consuntivoTask on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.consuntivoTask).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
