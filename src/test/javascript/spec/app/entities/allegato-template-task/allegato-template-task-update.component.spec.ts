import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { AllegatoTemplateTaskUpdateComponent } from 'app/entities/allegato-template-task/allegato-template-task-update.component';
import { AllegatoTemplateTaskService } from 'app/entities/allegato-template-task/allegato-template-task.service';
import { AllegatoTemplateTask } from 'app/shared/model/allegato-template-task.model';

describe('Component Tests', () => {
  describe('AllegatoTemplateTask Management Update Component', () => {
    let comp: AllegatoTemplateTaskUpdateComponent;
    let fixture: ComponentFixture<AllegatoTemplateTaskUpdateComponent>;
    let service: AllegatoTemplateTaskService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [AllegatoTemplateTaskUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AllegatoTemplateTaskUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AllegatoTemplateTaskUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AllegatoTemplateTaskService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AllegatoTemplateTask(123);
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
        const entity = new AllegatoTemplateTask();
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
