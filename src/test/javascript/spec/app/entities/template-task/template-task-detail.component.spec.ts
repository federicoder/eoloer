import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { TemplateTaskDetailComponent } from 'app/entities/template-task/template-task-detail.component';
import { TemplateTask } from 'app/shared/model/template-task.model';

describe('Component Tests', () => {
  describe('TemplateTask Management Detail Component', () => {
    let comp: TemplateTaskDetailComponent;
    let fixture: ComponentFixture<TemplateTaskDetailComponent>;
    const route = ({ data: of({ templateTask: new TemplateTask(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [TemplateTaskDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TemplateTaskDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TemplateTaskDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load templateTask on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.templateTask).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
