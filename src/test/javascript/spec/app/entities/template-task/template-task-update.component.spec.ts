import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { TemplateTaskUpdateComponent } from 'app/entities/template-task/template-task-update.component';
import { TemplateTaskService } from 'app/entities/template-task/template-task.service';
import { TemplateTask } from 'app/shared/model/template-task.model';

describe('Component Tests', () => {
  describe('TemplateTask Management Update Component', () => {
    let comp: TemplateTaskUpdateComponent;
    let fixture: ComponentFixture<TemplateTaskUpdateComponent>;
    let service: TemplateTaskService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [TemplateTaskUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TemplateTaskUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TemplateTaskUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TemplateTaskService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TemplateTask(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TemplateTask();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
