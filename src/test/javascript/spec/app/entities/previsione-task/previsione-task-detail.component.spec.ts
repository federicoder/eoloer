import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { PrevisioneTaskDetailComponent } from 'app/entities/previsione-task/previsione-task-detail.component';
import { PrevisioneTask } from 'app/shared/model/previsione-task.model';

describe('Component Tests', () => {
  describe('PrevisioneTask Management Detail Component', () => {
    let comp: PrevisioneTaskDetailComponent;
    let fixture: ComponentFixture<PrevisioneTaskDetailComponent>;
    const route = ({ data: of({ previsioneTask: new PrevisioneTask(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [PrevisioneTaskDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PrevisioneTaskDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PrevisioneTaskDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load previsioneTask on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.previsioneTask).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
